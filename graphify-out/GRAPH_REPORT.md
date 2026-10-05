# Graph Report - quran  (2026-10-06)

## Corpus Check
- 75 files · ~31,516 words
- Verdict: corpus is large enough that graph structure adds value.
- Unclassified: 10 file(s) not represented in the graph (top: (none) 3, .properties 2, .xml 1)

## Summary
- 538 nodes · 913 edges · 51 communities (19 shown, 32 thin omitted)
- Extraction: 98% EXTRACTED · 2% INFERRED · 0% AMBIGUOUS · INFERRED: 16 edges (avg confidence: 0.9)
- Token cost: 0 input · 0 output

## Graph Freshness
- Built from commit: `79120ebb`
- Run `git rev-parse HEAD` and compare to check if the graph is stale.
- Run `graphify update .` after code changes (no API cost).

## Community Hubs (Navigation)
- ReaderViewModel.kt
- QuranApp.kt
- ReaderScreen.kt
- AudioPlayer.ios.kt
- SearchViewModel
- AppResult
- QuranApi.kt
- mcp
- ReaderViewModel
- rememberScreenClass
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
- AudioPlayer.android.kt
- IosAudioPlayer
- DesktopAudioPlayer
- AndroidAudioPlayer
- PlayerState
- Proposal: scaffold awal Quran KMP (MVVM+UDF + MCP)

## God Nodes (most connected - your core abstractions)
1. `ReaderViewModel` - 27 edges
2. `AudioPlayer` - 21 edges
3. `AppResult` - 20 edges
4. `QuranRepository` - 18 edges
5. `Ayah` - 17 edges
6. `QuranApp()` - 16 edges
7. `SearchViewModel` - 15 edges
8. `MviContract` - 14 edges
9. `Bookmark` - 14 edges
10. `ReaderEvent` - 14 edges

## Surprising Connections (you probably didn't know these)
- `Langkah berikut (tasks.md)` --references--> `SurahMetadata`  [INFERRED]
  openspec/changes/quran-kmp-init/proposal.md → composeApp/src/commonMain/kotlin/com/binarydev/quran/core/data/local/LocalStore.kt
- `Data (Madinah)` --references--> `SurahMetadata`  [INFERRED]
  openspec/specs/quran-mvp/spec.md → composeApp/src/commonMain/kotlin/com/binarydev/quran/core/data/local/LocalStore.kt
- `Alur spec (OpenSpec)` --references--> `QuranApi`  [INFERRED]
  AGENTS.md → composeApp/src/commonMain/kotlin/com/binarydev/quran/core/data/remote/api/QuranApi.kt
- `Arsitektur: MVVM + UDF` --references--> `UiEffect`  [INFERRED]
  README.md → composeApp/src/commonMain/kotlin/com/binarydev/quran/core/common/MviContract.kt
- `Aturan arsitektur (wajib)` --references--> `rememberScreenClass()`  [INFERRED]
  AGENTS.md → composeApp/src/commonMain/kotlin/com/binarydev/quran/core/designsystem/Responsive.kt

## Import Cycles
- None detected.

## Communities (51 total, 32 thin omitted)

### Community 0 - "ReaderViewModel.kt"
Cohesion: 0.07
Nodes (11): MviContract, InMemoryStore, BookmarkEvent, BookmarkState, BookmarkViewModel, Toggle, HomeEvent, OpenPage (+3 more)

### Community 1 - "QuranApp.kt"
Cohesion: 0.07
Nodes (10): MainActivity, PageReader, QuranApp(), SurahReader, QuranTheme(), HomeScreen(), SearchScreen(), SettingsScreen() (+2 more)

### Community 2 - "ReaderScreen.kt"
Cohesion: 0.06
Nodes (6): SurahData, MushafText(), uthmaniStyle(), MushafPageView(), MushafParagraph(), SurahHeader()

### Community 4 - "SearchViewModel"
Cohesion: 0.08
Nodes (23): UiEffect, HomeEffect, NavigatePage, NavigateSurah, Go, NavigateAyah, OpenAyah, Query (+15 more)

### Community 5 - "AppResult"
Cohesion: 0.07
Nodes (21): AppResult, Err, Loading, map(), Ok, SurahMetadata, toDomain(), QuranRepositoryImpl (+13 more)

### Community 6 - "QuranApi.kt"
Cohesion: 0.14
Nodes (8): engine(), QuranApi, PaginationDto, SearchBodyDto, SearchResponse, SearchResultDto, VerseDto, VersesResponse

### Community 7 - "mcp"
Cohesion: 0.09
Nodes (22): command, enabled, timeout, type, command, enabled, timeout, type (+14 more)

### Community 8 - "ReaderViewModel"
Cohesion: 0.08
Nodes (27): audioUrl(), Reciter, Reciters, AudioBar(), AudioError, LoadPage, LoadSurah, NextPage (+19 more)

### Community 9 - "rememberScreenClass"
Cohesion: 0.13
Nodes (13): AGENTS.md — cara kerja AI di repo ini, Alur spec (OpenSpec), Aturan arsitektur (wajib), MCP, Perintah cepat, rememberIsLandscape(), rememberScreenClass(), ScreenClass (+5 more)

### Community 10 - "Quran MVP — KMP ringan, Mushaf Utsmani Madinah"
Cohesion: 0.18
Nodes (10): Arsitektur (MVVM + UDF — wajib), Data (Madinah), Fungsional, Kriteria terima, Non-fungsional (ringan & responsif), Non-tujuan (MVP), Persona, Quran MVP — KMP ringan, Mushaf Utsmani Madinah (+2 more)

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
Cohesion: 0.19
Nodes (5): AudioPlayer, createAudioPlayer(), Finished, PlayerEvent, Tasks — mushaf-book-audio

### Community 44 - "Proposal: Mushaf mirip buku + audio tilawah + font Utsmani"
Cohesion: 0.33
Nodes (5): Di luar cakupan, Masalah, Proposal: Mushaf mirip buku + audio tilawah + font Utsmani, Sumber terverifikasi (curl, Okt 2026), Usulan

### Community 49 - "PlayerState"
Cohesion: 0.33
Nodes (6): Error, Idle, Loading, Paused, PlayerState, Playing

### Community 50 - "Proposal: scaffold awal Quran KMP (MVVM+UDF + MCP)"
Cohesion: 0.40
Nodes (4): Langkah berikut (tasks.md), Proposal: scaffold awal Quran KMP (MVVM+UDF + MCP), Ringkasan, Yang dibuat

## Knowledge Gaps
- **106 isolated node(s):** `PlatformCaps`, `Err`, `Loading`, `Idle`, `Loading` (+101 more)
  These have ≤1 connection - possible missing edges or undocumented components. (Counts symbols only; 235 node(s) total have ≤1 connection when file, concept and rationale nodes are included.)
- **32 thin communities (<3 nodes) omitted from report** — run `graphify query` to explore isolated nodes.

## Suggested Questions
_Questions this graph is uniquely positioned to answer:_

- **Why does `ReaderViewModel` connect `ReaderViewModel` to `ReaderViewModel.kt`, `AudioPlayer`, `AppResult`?**
  _High betweenness centrality (0.147) - this node is a cross-community bridge._
- **Why does `Ayah` connect `AppResult` to `ReaderViewModel`, `ReaderViewModel.kt`, `ReaderScreen.kt`, `SearchViewModel`?**
  _High betweenness centrality (0.104) - this node is a cross-community bridge._
- **Why does `AudioPlayer` connect `AudioPlayer` to `ReaderViewModel.kt`, `AppResult`, `ReaderViewModel`, `AudioPlayer.android.kt`, `IosAudioPlayer`, `DesktopAudioPlayer`, `AndroidAudioPlayer`, `PlayerState`?**
  _High betweenness centrality (0.103) - this node is a cross-community bridge._
- **What connects `PlatformCaps`, `Err`, `Loading` to the rest of the system?**
  _106 weakly-connected nodes found - possible documentation gaps or missing edges._
- **Should `ReaderViewModel.kt` be split into smaller, more focused modules?**
  _Cohesion score 0.07397959183673469 - nodes in this community are weakly interconnected._
- **Should `QuranApp.kt` be split into smaller, more focused modules?**
  _Cohesion score 0.07152496626180836 - nodes in this community are weakly interconnected._
- **Should `ReaderScreen.kt` be split into smaller, more focused modules?**
  _Cohesion score 0.05955734406438632 - nodes in this community are weakly interconnected._