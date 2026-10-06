# Graph Report - quran  (2026-10-06)

## Corpus Check
- 84 files · ~33,953 words
- Verdict: corpus is large enough that graph structure adds value.
- Unclassified: 11 file(s) not represented in the graph (top: (none) 3, .properties 2, .xml 1)

## Summary
- 627 nodes · 1124 edges · 62 communities (25 shown, 37 thin omitted)
- Extraction: 98% EXTRACTED · 2% INFERRED · 0% AMBIGUOUS · INFERRED: 19 edges (avg confidence: 0.89)
- Token cost: 0 input · 0 output

## Graph Freshness
- Built from commit: `13022e79`
- Run `git rev-parse HEAD` and compare to check if the graph is stale.
- Run `graphify update .` after code changes (no API cost).

## Community Hubs (Navigation)
- UiEffect
- QuranApp.kt
- AudioPlayer.android.kt
- Quran Madinah — Kotlin Multiplatform (ringan, responsif, mudah digunakan)
- AppResult
- QuranApi.kt
- mcp
- ReaderViewModel
- Bookmark
- Quran MVP — KMP ringan, Mushaf Utsmani Madinah
- openspec-explore/SKILL.md
- opsx-explore.md
- Memory Maintenance
- font/README.md
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
- MushafPageView.kt
- rememberScreenClass
- PlatformLocal.ios.kt
- AudioPlayer.ios.kt
- PlatformLocal.android.kt
- PlatformLocal.desktop.kt
- DesktopAudioPlayer
- HomeViewModel.kt
- SearchViewModel
- SettingsViewModel.kt
- IosAudioPlayer
- Proposal (FUTURE): Render pixel-identik via font QCF per halaman
- MviContract
- PlayerState
- Proposal: scaffold awal Quran KMP (MVVM+UDF + MCP)

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
- `Langkah berikut (tasks.md)` --references--> `SurahMetadata`  [INFERRED]
  openspec/changes/quran-kmp-init/proposal.md → composeApp/src/commonMain/kotlin/com/binarydev/quran/core/data/local/LocalStore.kt
- `Alur spec (OpenSpec)` --references--> `QuranApi`  [INFERRED]
  AGENTS.md → composeApp/src/commonMain/kotlin/com/binarydev/quran/core/data/remote/api/QuranApi.kt
- `Data (Madinah)` --references--> `SurahMetadata`  [INFERRED]
  openspec/specs/quran-mvp/spec.md → composeApp/src/commonMain/kotlin/com/binarydev/quran/core/data/local/LocalStore.kt
- `Aturan arsitektur (wajib)` --references--> `rememberScreenClass()`  [INFERRED]
  AGENTS.md → composeApp/src/commonMain/kotlin/com/binarydev/quran/core/designsystem/Responsive.kt

## Import Cycles
- None detected.

## Communities (62 total, 37 thin omitted)

### Community 0 - "UiEffect"
Cohesion: 0.24
Nodes (8): UiEffect, HomeEffect, HomeState, NavigatePage, NavigateSurah, NavigateAyah, SearchEffect, SearchState

### Community 1 - "QuranApp.kt"
Cohesion: 0.07
Nodes (11): MainActivity, PageReader, QuranApp(), QuranNavHost(), SurahReader, QuranTheme(), HomeScreen(), SearchScreen() (+3 more)

### Community 4 - "Quran Madinah — Kotlin Multiplatform (ringan, responsif, mudah digunakan)"
Cohesion: 0.33
Nodes (5): Arsitektur: MVVM + UDF, Jalankan, Mushaf Utsmani (Madinah), Pengembangan dengan AI, Quran Madinah — Kotlin Multiplatform (ringan, responsif, mudah digunakan)

### Community 5 - "AppResult"
Cohesion: 0.10
Nodes (21): AppResult, Err, Loading, map(), Ok, SurahMetadata, toDomain(), toPageLines() (+13 more)

### Community 6 - "QuranApi.kt"
Cohesion: 0.14
Nodes (9): engine(), QuranApi, PaginationDto, SearchBodyDto, SearchResponse, SearchResultDto, VerseDto, VersesResponse (+1 more)

### Community 7 - "mcp"
Cohesion: 0.09
Nodes (22): command, enabled, timeout, type, command, enabled, timeout, type (+14 more)

### Community 8 - "ReaderViewModel"
Cohesion: 0.08
Nodes (26): audioUrl(), Reciter, Reciters, AudioError, LoadPage, LoadSurah, Message, NavigatePage (+18 more)

### Community 9 - "Bookmark"
Cohesion: 0.12
Nodes (7): BookmarkRepositoryImpl, Bookmark, BookmarkRepository, BookmarkEvent, BookmarkState, BookmarkViewModel, Toggle

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

### Community 15 - "ContentView"
Cohesion: 0.40
Nodes (3): ContentView, .body, SwiftUI

### Community 43 - "AudioPlayer"
Cohesion: 0.17
Nodes (6): AudioPlayer, createAudioPlayer(), Finished, PlayerEvent, createAudioPlayer(), Tasks — mushaf-book-audio

### Community 44 - "Proposal: Mushaf mirip buku + audio tilawah + font Utsmani"
Cohesion: 0.33
Nodes (5): Di luar cakupan, Masalah, Proposal: Mushaf mirip buku + audio tilawah + font Utsmani, Sumber terverifikasi (curl, Okt 2026), Usulan

### Community 45 - "Di.kt"
Cohesion: 0.08
Nodes (7): createDatabase(), createDbDriver(), dataFilePath(), createSettingsStore(), SettingKeys, SettingsRepositoryImpl, SettingsRepository

### Community 46 - "MushafPageView.kt"
Cohesion: 0.05
Nodes (17): SurahData, MushafText(), uthmaniStyle(), AudioBar(), ExactLinesPage(), FitLine(), MushafPageView(), MushafParagraph() (+9 more)

### Community 47 - "rememberScreenClass"
Cohesion: 0.13
Nodes (12): AGENTS.md — cara kerja AI di repo ini, Alur spec (OpenSpec), Aturan arsitektur (wajib), MCP, Perintah cepat, rememberIsLandscape(), rememberScreenClass(), ScreenClass (+4 more)

### Community 52 - "PlatformLocal.desktop.kt"
Cohesion: 0.43
Nodes (3): appDir(), createDbDriver(), dataFilePath()

### Community 54 - "HomeViewModel.kt"
Cohesion: 0.27
Nodes (5): HomeEvent, OpenPage, OpenSurah, Search, HomeViewModel

### Community 55 - "SearchViewModel"
Cohesion: 0.27
Nodes (5): Go, OpenAyah, Query, SearchEvent, SearchViewModel

### Community 56 - "SettingsViewModel.kt"
Cohesion: 0.31
Nodes (6): ArabScale, Dark, Latin, SettingsEvent, SettingsState, SettingsViewModel

### Community 58 - "Proposal (FUTURE): Render pixel-identik via font QCF per halaman"
Cohesion: 0.29
Nodes (6): Kriteria mulai, Latar, Pendekatan QCF (seperti quran.com web), Proposal (FUTURE): Render pixel-identik via font QCF per halaman, Risiko utama, Status

### Community 60 - "PlayerState"
Cohesion: 0.33
Nodes (6): Error, Idle, Loading, Paused, PlayerState, Playing

### Community 61 - "Proposal: scaffold awal Quran KMP (MVVM+UDF + MCP)"
Cohesion: 0.40
Nodes (4): Langkah berikut (tasks.md), Proposal: scaffold awal Quran KMP (MVVM+UDF + MCP), Ringkasan, Yang dibuat

## Knowledge Gaps
- **114 isolated node(s):** `PlatformCaps`, `Err`, `Loading`, `Idle`, `Loading` (+109 more)
  These have ≤1 connection - possible missing edges or undocumented components. (Counts symbols only; 263 node(s) total have ≤1 connection when file, concept and rationale nodes are included.)
- **37 thin communities (<3 nodes) omitted from report** — run `graphify query` to explore isolated nodes.

## Suggested Questions
_Questions this graph is uniquely positioned to answer:_

- **Why does `AudioPlayer` connect `AudioPlayer` to `AudioPlayer.android.kt`, `ReaderViewModel`, `Di.kt`, `ReaderViewModel.kt`, `DesktopAudioPlayer`, `IosAudioPlayer`, `PlayerState`?**
  _High betweenness centrality (0.083) - this node is a cross-community bridge._
- **Why does `ReaderViewModel` connect `ReaderViewModel` to `AppResult`, `Bookmark`, `AudioPlayer`, `Di.kt`, `MushafPageView.kt`, `ReaderViewModel.kt`, `MviContract`?**
  _High betweenness centrality (0.081) - this node is a cross-community bridge._
- **Why does `ReaderScreen()` connect `MushafPageView.kt` to `ReaderViewModel`, `QuranApp.kt`, `AudioPlayer`, `Bookmark`?**
  _High betweenness centrality (0.057) - this node is a cross-community bridge._
- **What connects `PlatformCaps`, `Err`, `Loading` to the rest of the system?**
  _114 weakly-connected nodes found - possible documentation gaps or missing edges._
- **Should `QuranApp.kt` be split into smaller, more focused modules?**
  _Cohesion score 0.06553911205073996 - nodes in this community are weakly interconnected._
- **Should `AppResult` be split into smaller, more focused modules?**
  _Cohesion score 0.09863945578231292 - nodes in this community are weakly interconnected._
- **Should `QuranApi.kt` be split into smaller, more focused modules?**
  _Cohesion score 0.1396011396011396 - nodes in this community are weakly interconnected._