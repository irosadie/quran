# Graph Report - quran  (2026-10-06)

## Corpus Check
- 83 files · ~32,493 words
- Verdict: corpus is large enough that graph structure adds value.
- Unclassified: 11 file(s) not represented in the graph (top: (none) 3, .properties 2, .xml 1)

## Summary
- 602 nodes · 1047 edges · 52 communities (21 shown, 31 thin omitted)
- Extraction: 98% EXTRACTED · 2% INFERRED · 0% AMBIGUOUS · INFERRED: 18 edges (avg confidence: 0.89)
- Token cost: 0 input · 0 output

## Graph Freshness
- Built from commit: `cbb68220`
- Run `git rev-parse HEAD` and compare to check if the graph is stale.
- Run `graphify update .` after code changes (no API cost).

## Community Hubs (Navigation)
- ReaderViewModel.kt
- QuranApp.kt
- AudioPlayer.ios.kt
- Bookmark
- Ayah
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
- MushafPageView.kt
- MushafText.kt
- ReaderScreen.kt
- PlatformLocal.ios.kt
- AudioBar.kt
- PlatformLocal.android.kt
- PlatformLocal.desktop.kt

## God Nodes (most connected - your core abstractions)
1. `ReaderViewModel` - 27 edges
2. `AudioPlayer` - 21 edges
3. `Ayah` - 21 edges
4. `Bookmark` - 21 edges
5. `AppResult` - 20 edges
6. `QuranRepository` - 18 edges
7. `QuranApp()` - 16 edges
8. `SearchViewModel` - 15 edges
9. `MviContract` - 14 edges
10. `BookmarkRepository` - 14 edges

## Surprising Connections (you probably didn't know these)
- `Alur spec (OpenSpec)` --references--> `QuranApi`  [INFERRED]
  AGENTS.md → composeApp/src/commonMain/kotlin/com/binarydev/quran/core/data/remote/api/QuranApi.kt
- `Arsitektur: MVVM + UDF` --references--> `UiEffect`  [INFERRED]
  README.md → composeApp/src/commonMain/kotlin/com/binarydev/quran/core/common/MviContract.kt
- `Tasks — mushaf-book-audio` --references--> `AudioPlayer`  [INFERRED]
  openspec/changes/mushaf-book-audio/tasks.md → composeApp/src/commonMain/kotlin/com/binarydev/quran/core/data/audio/AudioPlayer.kt
- `Langkah berikut (tasks.md)` --references--> `SurahMetadata`  [INFERRED]
  openspec/changes/quran-kmp-init/proposal.md → composeApp/src/commonMain/kotlin/com/binarydev/quran/core/data/local/LocalStore.kt
- `Data (Madinah)` --references--> `SurahMetadata`  [INFERRED]
  openspec/specs/quran-mvp/spec.md → composeApp/src/commonMain/kotlin/com/binarydev/quran/core/data/local/LocalStore.kt

## Import Cycles
- None detected.

## Communities (52 total, 31 thin omitted)

### Community 0 - "ReaderViewModel.kt"
Cohesion: 0.07
Nodes (14): MviContract, QuranRepository, GetMushafPageUseCase, SearchAyahUseCase, HomeEvent, OpenPage, OpenSurah, Search (+6 more)

### Community 1 - "QuranApp.kt"
Cohesion: 0.05
Nodes (21): AGENTS.md — cara kerja AI di repo ini, Alur spec (OpenSpec), Aturan arsitektur (wajib), MCP, Perintah cepat, PageReader, QuranApp(), SurahReader (+13 more)

### Community 3 - "AudioPlayer.ios.kt"
Cohesion: 0.07
Nodes (5): AndroidAudioPlayer, createAudioPlayer(), createAudioPlayer(), FinishDelegate, IosAudioPlayer

### Community 4 - "Bookmark"
Cohesion: 0.09
Nodes (17): UiEffect, BookmarkRepositoryImpl, Bookmark, BookmarkRepository, BookmarkEvent, BookmarkState, BookmarkViewModel, Toggle (+9 more)

### Community 5 - "Ayah"
Cohesion: 0.07
Nodes (22): AppResult, Err, Loading, map(), Ok, SurahMetadata, toDomain(), QuranRepositoryImpl (+14 more)

### Community 6 - "QuranApi.kt"
Cohesion: 0.14
Nodes (8): engine(), QuranApi, PaginationDto, SearchBodyDto, SearchResponse, SearchResultDto, VerseDto, VersesResponse

### Community 7 - "mcp"
Cohesion: 0.09
Nodes (22): command, enabled, timeout, type, command, enabled, timeout, type (+14 more)

### Community 8 - "ReaderViewModel"
Cohesion: 0.09
Nodes (23): audioUrl(), Reciter, Reciters, AudioError, LoadPage, LoadSurah, NextPage, PauseResume (+15 more)

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
Cohesion: 0.10
Nodes (12): AudioPlayer, createAudioPlayer(), Error, Finished, Idle, Loading, Paused, PlayerEvent (+4 more)

### Community 44 - "Proposal: Mushaf mirip buku + audio tilawah + font Utsmani"
Cohesion: 0.33
Nodes (5): Di luar cakupan, Masalah, Proposal: Mushaf mirip buku + audio tilawah + font Utsmani, Sumber terverifikasi (curl, Okt 2026), Usulan

### Community 45 - "Di.kt"
Cohesion: 0.07
Nodes (13): createDatabase(), createDbDriver(), dataFilePath(), createSettingsStore(), SettingKeys, SettingsRepositoryImpl, SettingsRepository, ArabScale (+5 more)

### Community 46 - "MushafPageView.kt"
Cohesion: 0.13
Nodes (4): MushafPageView(), MushafParagraph(), OrnamentPanel(), SurahHeader()

### Community 48 - "ReaderScreen.kt"
Cohesion: 0.17
Nodes (7): SurahData, isSpread(), MushafPager(), MushafTopBar(), ReaderScreen(), SpreadBook(), Tasks — mushaf-book-audio

### Community 51 - "PlatformLocal.android.kt"
Cohesion: 0.19
Nodes (3): createDbDriver(), MainActivity, AppContext

### Community 52 - "PlatformLocal.desktop.kt"
Cohesion: 0.43
Nodes (3): appDir(), createDbDriver(), dataFilePath()

## Knowledge Gaps
- **107 isolated node(s):** `PlatformCaps`, `Err`, `Loading`, `Idle`, `Loading` (+102 more)
  These have ≤1 connection - possible missing edges or undocumented components. (Counts symbols only; 255 node(s) total have ≤1 connection when file, concept and rationale nodes are included.)
- **31 thin communities (<3 nodes) omitted from report** — run `graphify query` to explore isolated nodes.

## Suggested Questions
_Questions this graph is uniquely positioned to answer:_

- **Why does `ReaderViewModel` connect `ReaderViewModel` to `ReaderViewModel.kt`, `Bookmark`, `Ayah`, `AudioPlayer`, `Di.kt`, `ReaderScreen.kt`?**
  _High betweenness centrality (0.098) - this node is a cross-community bridge._
- **Why does `Ayah` connect `Ayah` to `ReaderViewModel.kt`, `Bookmark`, `QuranApi.kt`, `ReaderViewModel`, `MushafPageView.kt`?**
  _High betweenness centrality (0.098) - this node is a cross-community bridge._
- **Why does `AudioPlayer` connect `AudioPlayer` to `ReaderViewModel.kt`, `AudioPlayer.ios.kt`, `ReaderViewModel`, `Di.kt`, `ReaderScreen.kt`?**
  _High betweenness centrality (0.088) - this node is a cross-community bridge._
- **What connects `PlatformCaps`, `Err`, `Loading` to the rest of the system?**
  _107 weakly-connected nodes found - possible documentation gaps or missing edges._
- **Should `ReaderViewModel.kt` be split into smaller, more focused modules?**
  _Cohesion score 0.07400555041628122 - nodes in this community are weakly interconnected._
- **Should `QuranApp.kt` be split into smaller, more focused modules?**
  _Cohesion score 0.05254901960784314 - nodes in this community are weakly interconnected._
- **Should `AudioPlayer.ios.kt` be split into smaller, more focused modules?**
  _Cohesion score 0.07317073170731707 - nodes in this community are weakly interconnected._