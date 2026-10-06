# Proposal (FUTURE): Render pixel-identik via font QCF per halaman

## Status
Disetujui user sebagai langkah "nanti kalau masih kurang". JANGAN implementasi
sebelum font KFGQPC tunggal dinilai kurang oleh user.

## Latar
Font Uthman Taha Naskh tunggal sudah ~90% mirip cetakan. Sisa 10%: kashida
otomatis (pemanjang huruf untuk justify) dan glif per-baris persis khat.

## Pendekatan QCF (seperti quran.com web)
- API: `GET /quran/verses/code_v2` (+ `code_v1`) memberi kode glyph per ayat.
- Font: 604 file WOFF2 (satu per halaman) dari CDN Quran Foundation — unduh
  on-demand per halaman, cache di disk (batas ~50MB LRU).
- WOFF2 tidak didukung native Android/iOS: konversi ke TTF saat unduh
  (butuh decoder murni-Kotlin/native) ATAU render via glyph path manual.
- Muat font file per platform: Android `Typeface.createFromFile` (+FontFamily
  expect/actual), iOS `CTFontManagerRegisterFontsForURL`, Desktop `Font(file)`.
- Tap-per-ayat: petakan rentang glyph → verse_key (dari respons code_* yang
  menyertakan verse_key per baris kode).

## Risiko utama
- Decoder WOFF2 di KMP (belum ada lib matang) — mungkin perlu microservice
  konversi atau bundel TTF hasil konversi offline (604 file di CDN sendiri).
- Tap/highlight per ayat lebih rumit (per glyph-range, bukan per kata).
- Lisensi font QCF: cek ulang ketentuan distribusi per-file.

## Kriteria mulai
User menyatakan font tunggal kurang setelah memakai 1 minggu.
