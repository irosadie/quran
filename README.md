# Quran Madinah — Kotlin Multiplatform (ringan, responsif, mudah digunakan)

Mushaf Utsmani Madinah (QCF V2, 604 halaman) untuk **Android • iOS • Desktop** dalam satu codebase Compose Multiplatform.

## Arsitektur: MVVM + UDF

```
composeApp/src/commonMain/kotlin/com/binarydev/quran/
├── app/                    # QuranApp.kt (Nav adaptif), Di.kt (Koin)
├── core/
│   ├── common/             # MviContract.kt, AppResult.kt
│   ├── designsystem/       # Theme.kt, MushafText.kt (RTL Utsmani), Responsive.kt
│   ├── domain/
│   │   ├── model/          # Surah, Ayah, MushafPage, Bookmark
│   │   ├── repository/     # interface Quran/Bookmark/Settings
│   │   └── usecase/        # GetMushafPageUseCase, SearchAyahUseCase
│   └── data/
│       ├── remote/api/     # QuranApi (Quran Foundation v4, mushaf=1 QCF V2)
│       ├── remote/dto/     # VerseDto
│       ├── local/          # SurahMetadata offline + InMemoryStore
│       ├── mapper/         # DTO -> domain
│       └── repository/     # QuranRepositoryImpl (cache memori)
└── feature/
    ├── home/presentation/      # HomeContract/State/Event/Effect + ViewModel + Screen
    ├── reader/presentation/    # mode SURAH & MUSHAF (halaman 1..604)
    ├── search/presentation/
    ├── bookmark/presentation/
    └── settings/presentation/
```

**UDF di tiap fitur:** `UiState` (immutable) → `UiEvent` (sealed) → `ViewModel.reduce` → `UiEffect` (Channel sekali-pakai untuk navigasi/snackbar).

## Mushaf Utsmani (Madinah)

- Layout: **halaman Mushaf Madinah 604 halaman** via `GET /verses/by_page/{1..604}`.
- Teks: `fields=text_uthmani` + `page_number`/`juz_number` per ayat (Quran.com API v4 publik, tanpa auth).
- Font: unduh **KFGQPC Hafs** di https://fonts.qurancomplex.gov.sa (atau mirror https://github.com/thetruetruth/quran-data-kfgqpc), taruh TTF/WOFF2 di `composeApp/src/commonMain/composeResources/font/` lalu daftarkan di `MushafText.kt`.
- Data dev resmi: https://qurancomplex.gov.sa/quran-dev (JSON/SQL/CSV).
- Referensi page-layout: https://api-docs.quran.foundation/docs/tutorials/fonts/page-layout/

## Jalankan

```bash
export JAVA_HOME=/opt/homebrew/opt/openjdk@17
export PATH="$JAVA_HOME/bin:$PATH"
./gradlew :composeApp:assembleDebug        # Android
./gradlew :composeApp:run                  # Desktop (JVM)
# iOS: buka iosApp di Xcode, hubungkan MainViewController()
```

## Pengembangan dengan AI

| Tool | Fungsi | Lokasi |
|---|---|---|
| Serena MCP | navigasi simbolik kode | `opencode.json` → `serena` + `.serena/` |
| OpenSpec | spec-driven (`/opsx:*`) | `openspec/` + `.opencode/skills/` |
| Headroom | kompresi konteks | `opencode.json` → `headroom` |
| RTK (Rust Token Killer) | hemat token CLI 60–90% | `.opencode/plugins/rtk.ts` + `rtk` CLI |
| Graphify | knowledge graph persisten | `.graphify/` + `raw/` |
| Quran MCP | data ayat/tafsir via Quran.com v4 | `opencode.json` → `quran-api` |

Lihat `AGENTS.md` untuk workflow harian.
