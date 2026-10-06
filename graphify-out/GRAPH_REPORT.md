# Graph Report - quran  (2026-10-06)

## Corpus Check
- 83 files · ~33,771 words
- Verdict: corpus is large enough that graph structure adds value.
- Unclassified: 11 file(s) not represented in the graph (top: (none) 3, .properties 2, .xml 1)

## Summary
- 622 nodes · 1121 edges · 49 communities (20 shown, 29 thin omitted)
- Extraction: 98% EXTRACTED · 2% INFERRED · 0% AMBIGUOUS · INFERRED: 19 edges (avg confidence: 0.89)
- Token cost: 0 input · 0 output

## Graph Freshness
- Built from commit: `0f22f519`
- Run `git rev-parse HEAD` and compare to check if the graph is stale.
- Run `graphify update .` after code changes (no API cost).

## Community Hubs (Navigation)
- SearchViewModel.kt
- QuranApp.kt
- MushafPageView.kt
- AudioPlayer.ios.kt
- Quran Madinah — Kotlin Multiplatform (ringan, responsif, mudah digunakan)
- AppResult
- QuranApi.kt
- mcp
- ReaderViewModel
- Quran MVP — KMP ringan, Mushaf Utsmani Madinah
- openspec-explore/SKILL.md
- opsx-explore.md
- Memory Maintenance
- Font Mushaf Madinah (KFGQPC / QCF V2)
- ContentView
- Engine.android.kt
- Engine.desktop.kt
- Engine.ios.kt
- Platform.android.kt
- Platform.kt
- Platform.desktop.kt
- Platform.ios.kt
- tasks.md
- AudioPlayer
- Proposal: Mushaf mirip buku + audio tilawah + font Utsmani
- Di.kt
- ReaderScreen.kt
- PlatformLocal.ios.kt
- PlatformLocal.android.kt
- PlatformLocal.desktop.kt

## God Nodes (most connected - your core abstractions)
1. `ReaderViewModel` - 26 edges
2. `AppResult` - 21 edges
3. `AudioPlayer` - 21 edges
4. `Ayah` - 21 edges
5. `Bookmark` - 21 edges
6. `QuranRepository` - 18 edges
7. `ReaderEvent` - 15 edges
8. `ReaderScreen()` - 15 edges
9. `SearchViewModel` - 15 edges
10. `MviContract` - 14 edges

## Surprising Connections (you probably didn't know these)
- `Arsitektur: MVVM + UDF` --references--> `UiEffect`  [INFERRED]
  README.md → composeApp/src/commonMain/kotlin/com/binarydev/quran/core/common/MviContract.kt
- `Alur spec (OpenSpec)` --references--> `QuranApi`  [INFERRED]
  AGENTS.md → composeApp/src/commonMain/kotlin/com/binarydev/quran/core/data/remote/api/QuranApi.kt
- `Langkah berikut (tasks.md)` --references--> `SurahMetadata`  [INFERRED]
  openspec/changes/quran-kmp-init/proposal.md → composeApp/src/commonMain/kotlin/com/binarydev/quran/core/data/local/LocalStore.kt
- `Data (Madinah)` --references--> `SurahMetadata`  [INFERRED]
  openspec/specs/quran-mvp/spec.md → composeApp/src/commonMain/kotlin/com/binarydev/quran/core/data/local/LocalStore.kt
- `Aturan arsitektur (wajib)` --references--> `rememberScreenClass()`  [INFERRED]
  AGENTS.md → composeApp/src/commonMain/kotlin/com/binarydev/quran/core/designsystem/Responsive.kt

## Import Cycles
- None detected.

## Communities (49 total, 29 thin omitted)

### Community 0 - "SearchViewModel.kt"
Cohesion: 0.06
Nodes (29): MviContract, UiEffect, BookmarkEvent, BookmarkState, BookmarkViewModel, Toggle, HomeEffect, HomeEvent (+21 more)

### Community 1 - "QuranApp.kt"
Cohesion: 0.07
Nodes (10): MainActivity, PageReader, QuranApp(), QuranNavHost(), SurahReader, QuranTheme(), HomeScreen(), SettingsScreen() (+2 more)

### Community 2 - "MushafPageView.kt"
Cohesion: 0.08
Nodes (11): SurahData, MushafText(), uthmaniStyle(), ExactLinesPage(), FitLine(), MushafPageView(), MushafParagraph(), OrnamentPanel() (+3 more)

### Community 3 - "AudioPlayer.ios.kt"
Cohesion: 0.09
Nodes (5): AndroidAudioPlayer, createAudioPlayer(), createAudioPlayer(), FinishDelegate, IosAudioPlayer

### Community 4 - "Quran Madinah — Kotlin Multiplatform (ringan, responsif, mudah digunakan)"
Cohesion: 0.33
Nodes (5): Arsitektur: MVVM + UDF, Jalankan, Mushaf Utsmani (Madinah), Pengembangan dengan AI, Quran Madinah — Kotlin Multiplatform (ringan, responsif, mudah digunakan)

### Community 5 - "AppResult"
Cohesion: 0.08
Nodes (25): AppResult, Err, Loading, map(), Ok, SurahMetadata, toDomain(), toPageLines() (+17 more)

### Community 6 - "QuranApi.kt"
Cohesion: 0.14
Nodes (9): engine(), QuranApi, PaginationDto, SearchBodyDto, SearchResponse, SearchResultDto, VerseDto, VersesResponse (+1 more)

### Community 7 - "mcp"
Cohesion: 0.09
Nodes (22): command, enabled, timeout, type, command, enabled, timeout, type (+14 more)

### Community 8 - "ReaderViewModel"
Cohesion: 0.07
Nodes (26): audioUrl(), Reciter, Reciters, AudioError, LoadPage, LoadSurah, Message, NavigatePage (+18 more)

### Community 10 - "Quran MVP — KMP ringan, Mushaf Utsmani Madinah"
Cohesion: 0.20
Nodes (9): Arsitektur (MVVM + UDF — wajib), Fungsional, Kriteria terima, Non-fungsional (ringan & responsif), Non-tujuan (MVP), Persona, Quran MVP — KMP ringan, Mushaf Utsmani Madinah, Requirements (+1 more)

### Community 11 - "openspec-explore/SKILL.md"
Cohesion: 0.17
Nodes (11): Check for context, Ending Discovery, Guardrails, Handling Different Entry Points, OpenSpec Awareness, Planning a Change, The Stance, What You Don't Have To Do (+3 more)

### Community 12 - "opsx-explore.md"
Cohesion: 0.18
Nodes (10): Check for context, Ending Discovery, Guardrails, OpenSpec Awareness, Planning a Change, The Stance, What You Don't Have To Do, What You Might Do (+2 more)

### Community 13 - "Memory Maintenance"
Cohesion: 0.33
Nodes (5): Add/update threshold, Discovery Model, Maintenance Actions, Memory Maintenance, Style

### Community 14 - "Font Mushaf Madinah (KFGQPC / QCF V2)"
Cohesion: 0.40
Nodes (4): Font Mushaf Madinah (KFGQPC / QCF V2), Lisensi, Pasang, Unduh (pilih satu)

### Community 15 - "ContentView"
Cohesion: 0.40
Nodes (3): ContentView, .body, SwiftUI

### Community 43 - "AudioPlayer"
Cohesion: 0.09
Nodes (13): AudioPlayer, createAudioPlayer(), Error, Finished, Idle, Loading, Paused, PlayerEvent (+5 more)

### Community 44 - "Proposal: Mushaf mirip buku + audio tilawah + font Utsmani"
Cohesion: 0.33
Nodes (5): Di luar cakupan, Masalah, Proposal: Mushaf mirip buku + audio tilawah + font Utsmani, Sumber terverifikasi (curl, Okt 2026), Usulan

### Community 45 - "Di.kt"
Cohesion: 0.06
Nodes (10): createDatabase(), createDbDriver(), dataFilePath(), createSettingsStore(), SettingKeys, SettingsRepositoryImpl, BookmarkRepositoryImpl, Bookmark (+2 more)

### Community 46 - "ReaderScreen.kt"
Cohesion: 0.06
Nodes (19): AGENTS.md — cara kerja AI di repo ini, Alur spec (OpenSpec), Aturan arsitektur (wajib), MCP, Perintah cepat, rememberIsLandscape(), rememberScreenClass(), ScreenClass (+11 more)

### Community 52 - "PlatformLocal.desktop.kt"
Cohesion: 0.43
Nodes (3): appDir(), createDbDriver(), dataFilePath()

## Knowledge Gaps
- **111 isolated node(s):** `PlatformCaps`, `Err`, `Loading`, `Idle`, `Loading` (+106 more)
  These have ≤1 connection - possible missing edges or undocumented components. (Counts symbols only; 258 node(s) total have ≤1 connection when file, concept and rationale nodes are included.)
- **29 thin communities (<3 nodes) omitted from report** — run `graphify query` to explore isolated nodes.

## Suggested Questions
_Questions this graph is uniquely positioned to answer:_

- **Why does `AudioPlayer` connect `AudioPlayer` to `ReaderViewModel`, `AudioPlayer.ios.kt`, `Di.kt`?**
  _High betweenness centrality (0.085) - this node is a cross-community bridge._
- **Why does `ReaderViewModel` connect `ReaderViewModel` to `SearchViewModel.kt`, `AppResult`, `AudioPlayer`, `Di.kt`, `ReaderScreen.kt`?**
  _High betweenness centrality (0.082) - this node is a cross-community bridge._
- **Why does `ReaderScreen()` connect `ReaderScreen.kt` to `SearchViewModel.kt`, `QuranApp.kt`, `MushafPageView.kt`, `ReaderViewModel`, `AudioPlayer`, `Di.kt`?**
  _High betweenness centrality (0.057) - this node is a cross-community bridge._
- **What connects `PlatformCaps`, `Err`, `Loading` to the rest of the system?**
  _111 weakly-connected nodes found - possible documentation gaps or missing edges._
- **Should `SearchViewModel.kt` be split into smaller, more focused modules?**
  _Cohesion score 0.05654761904761905 - nodes in this community are weakly interconnected._
- **Should `QuranApp.kt` be split into smaller, more focused modules?**
  _Cohesion score 0.06852497096399536 - nodes in this community are weakly interconnected._
- **Should `MushafPageView.kt` be split into smaller, more focused modules?**
  _Cohesion score 0.08194905869324474 - nodes in this community are weakly interconnected._