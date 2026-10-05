package com.binarydev.quran.feature.reader.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.binarydev.quran.core.common.AppResult
import com.binarydev.quran.core.common.MviContract
import com.binarydev.quran.core.data.audio.AudioPlayer
import com.binarydev.quran.core.data.audio.PlayerEvent
import com.binarydev.quran.core.data.audio.PlayerState
import com.binarydev.quran.core.data.audio.Reciter
import com.binarydev.quran.core.data.audio.audioUrl
import com.binarydev.quran.core.domain.model.Bookmark
import com.binarydev.quran.core.domain.model.MushafLine
import com.binarydev.quran.core.domain.repository.BookmarkRepository
import com.binarydev.quran.core.domain.repository.QuranRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock

class ReaderViewModel(
    private val repo: QuranRepository,
    private val bookmarks: BookmarkRepository,
    private val player: AudioPlayer,
) : ViewModel(), MviContract<ReaderState, ReaderEvent, ReaderEffect> {
    private val _state = MutableStateFlow(ReaderState())
    override val state: StateFlow<ReaderState> = _state.asStateFlow()
    private val _effect = Channel<ReaderEffect>(Channel.BUFFERED)
    val effect = _effect.receiveAsFlow()

    // Antrean putar: kunci "surah:ayah" berurutan + indeks posisi
    private var queue: List<String> = emptyList()
    private var queueIndex = -1

    // Prefetch: halaman yang sudah dihangatkan (repo meng-cache permanen)
    private var prefetchJob: Job? = null
    private val prefetched = mutableSetOf<Int>()

    init {
        // Cerminkan status pemutar ke UiState (UDF: satu sumber kebenaran di state)
        viewModelScope.launch {
            player.state.collect { ps ->
                _state.update { st ->
                    when (ps) {
                        is PlayerState.Loading -> st.copy(audioKey = ps.key, audioLoading = true, audioPlaying = false)
                        is PlayerState.Playing -> st.copy(audioKey = ps.key, audioLoading = false, audioPlaying = true)
                        is PlayerState.Paused -> st.copy(audioKey = ps.key, audioLoading = false, audioPlaying = false)
                        is PlayerState.Idle -> st.copy(audioKey = null, audioLoading = false, audioPlaying = false)
                        is PlayerState.Error -> st.copy(audioKey = null, audioLoading = false, audioPlaying = false)
                    }
                }
                if (ps is PlayerState.Error) _effect.send(ReaderEffect.AudioError(ps.message))
            }
        }
        viewModelScope.launch {
            player.events.collect { ev ->
                if (ev is PlayerEvent.Finished) onTrackFinished(ev.key)
            }
        }
    }

    override fun onEvent(event: ReaderEvent) {
        when (event) {
            is ReaderEvent.LoadSurah -> loadSurah(event.number)
            is ReaderEvent.LoadPage -> loadPage(event.page)
            is ReaderEvent.NextPage -> {
                val p = (_state.value.page + event.step).coerceIn(1, 604)
                loadPage(p)
            }
            is ReaderEvent.OpenMushafForSurah -> openMushafForSurah(event.number)
            is ReaderEvent.SetFontScale -> _state.update { it.copy(fontScale = event.scale.coerceIn(0.8f, 2f)) }
            ReaderEvent.Retry -> {
                val s = _state.value
                if (s.mode == ReadMode.MUSHAF) loadPage(s.page) else loadSurah(s.surah)
            }
            is ReaderEvent.PlayAyah -> playFromKey(event.key)
            ReaderEvent.PlayPage -> playPage()
            ReaderEvent.PauseResume -> pauseResume()
            ReaderEvent.StopAudio -> stopAudio()
            is ReaderEvent.SelectReciter -> selectReciter(event.reciter)
        }
    }

    private fun loadSurah(number: Int) {
        stopAudio()
        _state.update { it.copy(surah = number, mode = ReadMode.SURAH, loading = true, error = null) }
        viewModelScope.launch {
            when (val r = repo.ayahsBySurah(number)) {
                is AppResult.Ok -> {
                    _state.update { st -> st.copy(ayahs = r.data, loading = false) }
                    r.data.firstOrNull()?.let {
                        bookmarks.setLastRead(Bookmark(it.key, it.surah, it.ayah, createdAt = Clock.System.now().toEpochMilliseconds()))
                    }
                }
                is AppResult.Err -> _state.update { it.copy(loading = false, error = r.message) }
                AppResult.Loading -> Unit
            }
        }
    }

    private fun loadPage(page: Int) {        val p = page.coerceIn(1, 604)
        stopAudio()
        _state.update { it.copy(page = p, mode = ReadMode.MUSHAF, loading = true, error = null) }
        viewModelScope.launch {
            // Halaman saat ini + berikut (bentangan buku) dalam tata baris eksak.
            val cur = repo.pageLines(p)
            val nxt = if (p < 604) repo.pageLines(p + 1) else null
            if (cur is AppResult.Ok) {
                val next = (nxt as? AppResult.Ok)?.data
                _state.update { st ->
                    st.copy(
                        ayahs = cur.data.ayahs,
                        lines = cur.data.lines,
                        nextAyahs = next?.ayahs.orEmpty(),
                        nextLines = next?.lines.orEmpty(),
                        page = cur.data.page,
                        loading = false,
                    )
                }
                prefetchWindow(p)
            } else if (cur is AppResult.Err) {
                _state.update { it.copy(loading = false, error = cur.message) }
            }
        }
    }

    /**
     * Buka mode Mushaf di halaman PERTAMA surah (navigasi imersif PageReader).
     */
    private fun openMushafForSurah(number: Int) {
        viewModelScope.launch {
            when (val r = repo.firstPageOf(number, 1)) {
                is AppResult.Ok -> _effect.send(ReaderEffect.NavigatePage(r.data))
                is AppResult.Err -> _effect.send(ReaderEffect.Message(r.message))
                AppResult.Loading -> Unit
            }
        }
    }

    /**
     * Hangatkan a-4..a+4 (di luar halaman aktif) secara async paralel.
     * Hasil masuk cache repo → geser halaman terasa instan, tanpa memblokir UI.
     * Job lama dibatalkan tiap pindah halaman; yang gagal tidak ditandai
     * agar dicoba lagi saat jendela berikutnya mencakupnya.
     */
    private fun prefetchWindow(center: Int, radius: Int = PREFETCH_RADIUS) {
        prefetchJob?.cancel()
        prefetchJob = viewModelScope.launch {
            ((center - radius)..(center + radius))
                .filter { it in 1..604 && it != center && it !in prefetched }
                .map { n -> async { if (repo.pageLines(n) is AppResult.Ok) prefetched.add(n) } }
                .awaitAll()
        }
    }

    // ---- Audio tilawah (antrean = kunci ayat dari baris halaman) ----

    private fun playPage() {
        val keys = _state.value.lines.flatMap { it.segs }.map { it.k }.distinct()
        if (keys.isEmpty()) return
        queue = keys
        playIndex(0)
    }

    private fun playFromKey(key: String) {
        val current = _state.value.lines.flatMap { it.segs }.map { it.k }.distinct()
        val idx = current.indexOf(key)
        if (idx >= 0) {
            queue = current
            playIndex(idx)
            return
        }
        // Ayat di halaman sebelah (bentangan buku)
        val next = _state.value.nextLines.flatMap { it.segs }.map { it.k }.distinct()
        val idxNext = next.indexOf(key)
        if (idxNext >= 0) {
            queue = next
            playIndex(idxNext)
        }
    }

    private fun playIndex(i: Int) {
        queueIndex = i
        val key = queue.getOrNull(i) ?: return
        val (s, a) = key.split(":").mapNotNull { it.toIntOrNull() }
            .let { it.getOrElse(0) { 0 } to it.getOrElse(1) { 0 } }
        if (s <= 0 || a <= 0) return
        viewModelScope.launch { player.play(audioUrl(_state.value.reciter, s, a), key) }
    }

    private fun onTrackFinished(key: String) {
        if (queue.getOrNull(queueIndex) == key && queueIndex + 1 < queue.size) {
            playIndex(queueIndex + 1) // lanjut otomatis ke ayat berikut
        } else {
            stopAudio()
        }
    }

    private fun pauseResume() {
        val s = _state.value
        viewModelScope.launch {
            if (s.audioPlaying) player.pause()
            else if (s.audioKey != null) player.resume()
        }
    }

    private fun stopAudio() {
        queueIndex = -1
        viewModelScope.launch { player.stop() }
    }

    private fun selectReciter(reciter: Reciter) {
        val currentKey = _state.value.audioKey
        _state.update { it.copy(reciter = reciter) }
        // Ganti qari sambil jalan: ulangi ayat yang sedang berbunyi dengan suara baru.
        if (currentKey != null) playFromKey(currentKey)
    }

    override fun onCleared() {
        player.release()
    }

    companion object {
        /** Radius prefetch: a-4..a+4 di sekitar halaman terbuka. */
        const val PREFETCH_RADIUS = 4
    }
}
