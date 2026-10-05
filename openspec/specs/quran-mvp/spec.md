# Quran MVP — KMP ringan, Mushaf Utsmani Madinah

## Tujuan
Aplikasi Quran Kotlin Multiplatform yang **ringan, responsif, mudah digunakan** dengan teks
**Mushaf Utsmani cetakan Madinah (QCF V2, 604 halaman)** untuk Android, iOS, Desktop.

## Non-tujuan (MVP)
- Audio tilawah per kata, tafsir penuh, dan sinkronisasi cloud — fase berikutnya.
- Font bundel >5MB — font diunduh terpisah (lihat `composeResources/font/README.md`).

## Persona
- Pembaca harian (HP): buka → lanjut terakhir dibaca → baca 1 halaman (<3 detik).
- Pelajar (tablet/desktop): mode Mushaf 2-halaman + pencarian cepat.

## Requirements

### Fungsional
1. **Home 114 surah offline**: daftar + cari surah instan tanpa network.
2. **Mode Surah**: scroll ayat Utsmani per surah, tandai terakhir dibaca otomatis.
3. **Mode Mushaf ala buku**: teks mengalir kontinu RTL + penanda ayat, bingkai krem,
   header surah + Basmalah, nomor halaman; geser horizontal (pager RTL 604);
   landscape lebar = bentangan 2 halaman (ganjil kanan).
4. **Audio tilawah**: putar per halaman berurutan / tap ayat untuk mulai dari situ,
   sorot kuning ayat berbunyi, pilihan qari (Misyari, Husari, Minsyawi via everyayah);
   berhenti otomatis saat pindah halaman; Desktop menampilkan pesan belum tersedia.
4. **Pencarian**: min 2 huruf, debounce 500ms, hasil max 20; tap hasil membuka
   **halaman pertama** kemunculan ayat (ayat bersambung tetap mulai dari awal).
5. **Pengaturan**: mode malam, skala huruf Arab 80–200%, toggle latin/arti.
6. **Bookmark**: terakhir dibaca persisten (MVP: memori → DataStore; Room bila perlu).

### Non-fungsional (ringan & responsif)
- Lazy loading per surah/halaman + cache memori; **jangan preload 604 halaman**.
- Prefetch async: saat halaman A terbuka, hangatkan A±4 paralel di background
  (batalkan saat pindah halaman, hanya tandai yang sukses).
- Ktor Logging **mati** di rilis; timeout 15 dtk; pesan error Indonesia ringkas.
- Adaptif: `NavigationSuiteScaffold` — bottom-bar (compact) / rail (expanded).
- Arab selalu RTL, `text_uthmani`, font Amiri Quran, hormati `fontScale`.

## Arsitektur (MVVM + UDF — wajib)
- Tiap fitur: `*Contract.kt` (UiState/Event/Effect) + `*ViewModel.kt` (satu StateFlow + `onEvent`) + `*Screen.kt` (stateless).
- Aliran: Screen → `onEvent` → ViewModel → `state.update` / `_effect.send`.
- Lapisan: `core/domain` (model, repository interface, usecase) ← `core/data` (Ktor, DTO, mapper, repo impl) ← `feature/*/presentation`.

## Data (Madinah)
- Primer: **Quran.com API v4 publik** (`https://api.quran.com/api/v4`, tanpa auth).
  - `GET /verses/by_chapter/{n}?per_page=300&fields=text_uthmani` (satu request per surah).
  - `GET /verses/by_page/{1..604}?fields=text_uthmani` (halaman Mushaf Madinah).
  - `GET /search?q=&size=20` (respons `search.results`: `verse_key` + `text`).
- Catatan: endpoint `apis.quran.foundation` butuh header `x-auth-token`/`x-client-id`
  (HTTP 400 tanpa itu) — jangan dipakai langsung dari app.
- Fallback: bundel `SurahMetadata` offline + file QPC (https://qurancomplex.gov.sa/quran-dev).
- Validasi teks: via MCP `quran-api` (Quran.com v4).

## Kriteria terima
- [ ] Home tampil <1 dtk offline; cari "baqarah"/"2" ketemu Al-Baqarah.
- [ ] Buka surah 2 & halaman 604 tanpa crash; halaman di luar 1..604 di-clamp.
- [ ] Cari "rahmah" (≥2 huruf) tampil ≤20 hasil; query 1 huruf tidak hit API.
- [ ] HP (compact) bottom-bar; tablet/desktop (expanded) rail — tidak overflow.
- [ ] `./gradlew :composeApp:assembleDebug` hijau (JAVA_HOME JDK 17).
