# Proposal: Mushaf mirip buku + audio tilawah + font Utsmani

## Masalah
- Mode Mushaf saat ini daftar ayat per baris (tidak seperti buku), font serif bawaan
  (bukan Utsmani), dan **tidak ada audio** — belum memenuhi "kayak mushaf buku,
  bisa diputar suara, mushaf utsmani".

## Usulan
1. **Tampilan buku**: teks Arab mengalir kontinu per halaman (RTL, justify) dengan
   penanda nomor ayat, bingkai halaman krem, header nama surah + Basmalah,
   nomor halaman; geser horizontal (pager, arah RTL); landscape tablet =
   bentangan 2 halaman.
2. **Audio tilawah**: putar per ayat / per halaman berurutan, sorot ayat yang
   berbunyi, tap ayat untuk mulai dari situ, pilihan 3 qari (everyayah.com,
   gratis, tanpa auth). Android = MediaPlayer, iOS = AVPlayer, Desktop = stub
   (pesan "belum tersedia").
3. **Font Utsmani**: bundel Amiri Quran (OFL, 137KB) via composeResources.

## Sumber terverifikasi (curl, Okt 2026)
- Audio: `https://everyayah.com/data/{qari}/{SSSAAA}.mp3` — 200 OK untuk
  `Alafasy_128kbps`, `Husary_128kbps`, `Minshawy_Murattal_128kbps`,
  `Saood_ash-Shuraym_128kbps` (pakai 3 pertama).
- Font: `AmiriQuran-Regular.ttf` (TrueType, OFL, The Amiri Project).

## Di luar cakupan
- Tajwid berwarna, terjemahan per kata, unduh offline audio, audio Desktop.
