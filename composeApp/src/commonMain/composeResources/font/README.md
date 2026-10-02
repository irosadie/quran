# Font Mushaf Madinah (KFGQPC / QCF V2)

Aplikasi memakai teks `text_uthmani` + font Utsmani Madinah agar tampil persis cetakan Madinah.

## Unduh (pilih satu)
1. Resmi: https://fonts.qurancomplex.gov.sa — unduh **KFGQPC Hafs (Uthman Taha Naskh)** TTF.
2. Mirror dev: https://github.com/thetruetruth/quran-data-kfgqpc/tree/main/hafs (font + css + json per surah).

## Pasang
1. Taruh file, mis. `KFGQPC-Hafs.ttf`, di folder ini (`composeResources/font/`).
2. Daftarkan di `core/designsystem/MushafText.kt` via `FontFamily(Font(Res.font.KFGQPC_Hafs))`.
3. Android: salin juga ke `composeApp/src/androidMain/assets/` bila perlu fallback TextView.

## Lisensi
Font KFGQPC milik King Fahd Quran Printing Complex — gratis untuk aplikasi Quran, cantumkan atribusi di Settings → Tentang.
