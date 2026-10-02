# Graph Report - quran  (2026-10-02)

## Corpus Check
- 65 files · ~28,012 words
- Verdict: corpus is large enough that graph structure adds value.
- Unclassified: 9 file(s) not represented in the graph (top: (none) 3, .properties 2, .xml 1)

## Summary
- 393 nodes · 626 edges · 43 communities (15 shown, 28 thin omitted)
- Extraction: 99% EXTRACTED · 1% INFERRED · 0% AMBIGUOUS · INFERRED: 7 edges (avg confidence: 0.94)
- Token cost: 0 input · 0 output

## Community Hubs (Navigation)
- ReaderViewModel.kt
- QuranApp.kt
- ReaderScreen.kt
- AppResult
- MviContract
- Bookmark
- QuranApi.kt
- mcp
- ReaderViewModel
- Responsive.kt
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

## God Nodes (most connected - your core abstractions)
1. `AppResult` - 18 edges
2. `QuranApp()` - 16 edges
3. `QuranRepository` - 15 edges
4. `ReaderViewModel` - 15 edges
5. `MviContract` - 14 edges
6. `Bookmark` - 14 edges
7. `SearchViewModel` - 13 edges
8. `Ayah` - 12 edges
9. `HomeViewModel` - 12 edges
10. `InMemoryStore` - 11 edges

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

## Communities (43 total, 28 thin omitted)

### Community 0 - "ReaderViewModel.kt"
Cohesion: 0.07
Nodes (18): InMemoryStore, BookmarkEvent, BookmarkState, BookmarkViewModel, Toggle, HomeEffect, HomeEvent, NavigatePage (+10 more)

### Community 1 - "QuranApp.kt"
Cohesion: 0.07
Nodes (10): MainActivity, PageReader, QuranApp(), SurahReader, QuranTheme(), HomeScreen(), SearchScreen(), SettingsScreen() (+2 more)

### Community 3 - "AppResult"
Cohesion: 0.13
Nodes (9): AppResult, Err, Loading, map(), Ok, QuranRepositoryImpl, QuranRepository, GetMushafPageUseCase (+1 more)

### Community 4 - "MviContract"
Cohesion: 0.10
Nodes (16): MviContract, UiEffect, NavigateAyah, SearchEffect, SearchState, ArabScale, Dark, Latin (+8 more)

### Community 5 - "Bookmark"
Cohesion: 0.09
Nodes (17): SurahMetadata, toDomain(), Ayah, Bookmark, MushafPage, Revelation, MADANIYAH, MAKKIYAH (+9 more)

### Community 6 - "QuranApi.kt"
Cohesion: 0.14
Nodes (5): engine(), QuranApi, PaginationDto, VerseDto, VersesResponse

### Community 7 - "mcp"
Cohesion: 0.09
Nodes (22): command, enabled, timeout, type, command, enabled, timeout, type (+14 more)

### Community 8 - "ReaderViewModel"
Cohesion: 0.15
Nodes (14): LoadPage, LoadSurah, NextPage, ReaderEffect, ReaderEvent, ReaderState, ReadMode, MUSHAF (+6 more)

### Community 9 - "Responsive.kt"
Cohesion: 0.13
Nodes (12): AGENTS.md — cara kerja AI di repo ini, Alur spec (OpenSpec), Aturan arsitektur (wajib), MCP, Perintah cepat, rememberIsLandscape(), rememberScreenClass(), ScreenClass (+4 more)

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

## Knowledge Gaps
- **91 isolated node(s):** `PlatformCaps`, `Err`, `Loading`, `COMPACT`, `MEDIUM` (+86 more)
  These have ≤1 connection - possible missing edges or undocumented components. (Counts symbols only; 172 node(s) total have ≤1 connection when file, concept and rationale nodes are included.)
- **28 thin communities (<3 nodes) omitted from report** — run `graphify query` to explore isolated nodes.

## Suggested Questions
_Questions this graph is uniquely positioned to answer:_

- **Why does `ReaderViewModel` connect `ReaderViewModel` to `ReaderViewModel.kt`, `ReaderScreen.kt`, `AppResult`, `MviContract`?**
  _High betweenness centrality (0.116) - this node is a cross-community bridge._
- **Why does `ReaderScreen()` connect `ReaderScreen.kt` to `ReaderViewModel`, `QuranApp.kt`?**
  _High betweenness centrality (0.089) - this node is a cross-community bridge._
- **Why does `QuranApi` connect `QuranApi.kt` to `Responsive.kt`, `AppResult`, `Bookmark`?**
  _High betweenness centrality (0.078) - this node is a cross-community bridge._
- **What connects `PlatformCaps`, `Err`, `Loading` to the rest of the system?**
  _91 weakly-connected nodes found - possible documentation gaps or missing edges._
- **Should `ReaderViewModel.kt` be split into smaller, more focused modules?**
  _Cohesion score 0.07446808510638298 - nodes in this community are weakly interconnected._
- **Should `QuranApp.kt` be split into smaller, more focused modules?**
  _Cohesion score 0.07152496626180836 - nodes in this community are weakly interconnected._
- **Should `ReaderScreen.kt` be split into smaller, more focused modules?**
  _Cohesion score 0.12375533428165007 - nodes in this community are weakly interconnected._