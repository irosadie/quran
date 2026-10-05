# Tasks — mushaf-book-audio

- [x] Riset URL audio + font (terverifikasi curl)
- [x] `SurahData.kt`: latin+arab+jumlah ayat 114, tandai Madaniyah (ganti placeholder)
- [x] Font Amiri di `MushafText.kt` via `Res.font.amiri_quran`
- [x] `Reciter.kt` + `AudioPlayer` (interface, state, expect `createAudioPlayer`)
- [x] Actual Android (MediaPlayer), iOS (AVAudioPlayer streaming), Desktop (stub pesan)
- [x] DI: `single<AudioPlayer>` di `appModule`
- [x] Reader: state audio (key/playing/loading/reciter/nextAyahs) + event Play/Pause/Stop/Reciter
- [x] `MushafPageView.kt`: paragraf mengalir + highlight + tap ayat + bingkai + header
- [x] `AudioBar.kt`: putar halaman, jeda, ganti qari
- [x] `ReaderScreen`: pager RTL 604 + bentangan 2 halaman (expanded landscape)
- [x] Update `spec.md` (syarat buku + audio)
- [x] Build 3 target hijau + uji APK: buka halaman, putar audio, cek sorotan
  - Hasil uji emulator: Al-Fatihah tampil mirip buku; ▶ putar 1:1→lanjut otomatis;
    tap ayat loncat akurat; ganti qari ke Husari sambil jalan OK (lanjut 1:5)
