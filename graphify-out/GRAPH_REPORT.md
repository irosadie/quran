# Graph Report - quran  (2026-10-06)

## Corpus Check
- 84 files · ~34,083 words
- Verdict: corpus is large enough that graph structure adds value.
- Unclassified: 11 file(s) not represented in the graph (top: (none) 3, .properties 2, .xml 1)

## Summary
- 628 nodes · 1126 edges · 57 communities (20 shown, 37 thin omitted)
- Extraction: 98% EXTRACTED · 2% INFERRED · 0% AMBIGUOUS · INFERRED: 19 edges (avg confidence: 0.89)
- Token cost: 0 input · 0 output

## Graph Freshness
- Built from commit: `5581710a`
- Run `git rev-parse HEAD` and compare to check if the graph is stale.
- Run `graphify update .` after code changes (no API cost).

## Community Hubs (Navigation)
- SearchViewModel.kt
- QuranApp.kt
- AndroidAudioPlayer
- AppResult
- QuranApi.kt
- mcp
- ReaderViewModel
- ReaderScreen.kt
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
- MushafText.kt
- PlatformLocal.ios.kt
- AudioBar.kt
- PlatformLocal.desktop.kt
- DesktopAudioPlayer
- IosAudioPlayer
- Proposal (FUTURE): Render pixel-identik via font QCF per halaman
- PlayerState

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
- `Alur spec (OpenSpec)` --references--> `QuranApi`  [INFERRED]
  AGENTS.md → composeApp/src/commonMain/kotlin/com/binarydev/quran/core/data/remote/api/QuranApi.kt
- `Arsitektur: MVVM + UDF` --references--> `UiEffect`  [INFERRED]
  README.md → composeApp/src/commonMain/kotlin/com/binarydev/quran/core/common/MviContract.kt
- `Langkah berikut (tasks.md)` --references--> `SurahMetadata`  [INFERRED]
  openspec/changes/quran-kmp-init/proposal.md → composeApp/src/commonMain/kotlin/com/binarydev/quran/core/data/local/LocalStore.kt
- `Data (Madinah)` --references--> `SurahMetadata`  [INFERRED]
  openspec/specs/quran-mvp/spec.md → composeApp/src/commonMain/kotlin/com/binarydev/quran/core/data/local/LocalStore.kt
- `Aturan arsitektur (wajib)` --references--> `rememberScreenClass()`  [INFERRED]
  AGENTS.md → composeApp/src/commonMain/kotlin/com/binarydev/quran/core/designsystem/Responsive.kt

## Import Cycles
- None detected.

## Communities (57 total, 37 thin omitted)

### Community 0 - "SearchViewModel.kt"
Cohesion: 0.05
Nodes (34): MviContract, UiEffect, BookmarkEvent, BookmarkState, BookmarkViewModel, Toggle, HomeEffect, HomeEvent (+26 more)

### Community 1 - "QuranApp.kt"
Cohesion: 0.06
Nodes (13): createDbDriver(), MainActivity, AppContext, PageReader, QuranApp(), QuranNavHost(), SurahReader, QuranTheme() (+5 more)

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

### Community 9 - "ReaderScreen.kt"
Cohesion: 0.16
Nodes (6): SurahData, isSpread(), MushafPager(), MushafTopBar(), ReaderScreen(), SpreadBook()

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
Cohesion: 0.06
Nodes (10): createDatabase(), createDbDriver(), dataFilePath(), createSettingsStore(), SettingKeys, SettingsRepositoryImpl, BookmarkRepositoryImpl, Bookmark (+2 more)

### Community 46 - "MushafPageView.kt"
Cohesion: 0.14
Nodes (9): uthmaniStyle(), ExactLinesPage(), FitLine(), MushafPageView(), MushafParagraph(), OrnamentPanel(), ParagraphFallback(), SurahHeader() (+1 more)

### Community 47 - "rememberScreenClass"
Cohesion: 0.13
Nodes (12): AGENTS.md — cara kerja AI di repo ini, Alur spec (OpenSpec), Aturan arsitektur (wajib), MCP, Perintah cepat, rememberIsLandscape(), rememberScreenClass(), ScreenClass (+4 more)

### Community 52 - "PlatformLocal.desktop.kt"
Cohesion: 0.43
Nodes (3): appDir(), createDbDriver(), dataFilePath()

### Community 58 - "Proposal (FUTURE): Render pixel-identik via font QCF per halaman"
Cohesion: 0.29
Nodes (6): Kriteria mulai, Latar, Pendekatan QCF (seperti quran.com web), Proposal (FUTURE): Render pixel-identik via font QCF per halaman, Risiko utama, Status

### Community 60 - "PlayerState"
Cohesion: 0.33
Nodes (6): Error, Idle, Loading, Paused, PlayerState, Playing

## Knowledge Gaps
- **114 isolated node(s):** `PlatformCaps`, `Err`, `Loading`, `Idle`, `Loading` (+109 more)
  These have ≤1 connection - possible missing edges or undocumented components. (Counts symbols only; 263 node(s) total have ≤1 connection when file, concept and rationale nodes are included.)
- **37 thin communities (<3 nodes) omitted from report** — run `graphify query` to explore isolated nodes.

## Suggested Questions
_Questions this graph is uniquely positioned to answer:_

- **Why does `AudioPlayer` connect `AudioPlayer` to `AndroidAudioPlayer`, `ReaderViewModel`, `Di.kt`, `DesktopAudioPlayer`, `IosAudioPlayer`, `PlayerState`?**
  _High betweenness centrality (0.083) - this node is a cross-community bridge._
- **Why does `ReaderViewModel` connect `ReaderViewModel` to `SearchViewModel.kt`, `AppResult`, `ReaderScreen.kt`, `AudioPlayer`, `Di.kt`?**
  _High betweenness centrality (0.080) - this node is a cross-community bridge._
- **Why does `ReaderScreen()` connect `ReaderScreen.kt` to `SearchViewModel.kt`, `QuranApp.kt`, `ReaderViewModel`, `AudioPlayer`, `Di.kt`, `MushafPageView.kt`, `MushafText.kt`, `AudioBar.kt`?**
  _High betweenness centrality (0.056) - this node is a cross-community bridge._
- **What connects `PlatformCaps`, `Err`, `Loading` to the rest of the system?**
  _114 weakly-connected nodes found - possible documentation gaps or missing edges._
- **Should `SearchViewModel.kt` be split into smaller, more focused modules?**
  _Cohesion score 0.05081585081585081 - nodes in this community are weakly interconnected._
- **Should `QuranApp.kt` be split into smaller, more focused modules?**
  _Cohesion score 0.05568627450980392 - nodes in this community are weakly interconnected._
- **Should `AppResult` be split into smaller, more focused modules?**
  _Cohesion score 0.08455625436757512 - nodes in this community are weakly interconnected._