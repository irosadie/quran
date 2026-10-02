# Proposal: scaffold awal Quran KMP (MVVM+UDF + MCP)

## Ringkasan
Scaffold satu modul `composeApp` (Android+iOS+Desktop) dengan 5 fitur UDF,
data Mushaf Madinah via Quran Foundation v4, dan tooling AI
(Serena, OpenSpec, Headroom, RTK, Graphify, Quran MCP).

## Yang dibuat
- Gradle KMP + Compose MP + Koin + Ktor + DataStore + Navigation.
- `core/{common,designsystem,domain,data}` + `feature/{home,reader,search,bookmark,settings}`.
- `opencode.json` (MCP), `.opencode/plugins/rtk.ts`, `openspec/specs/quran-mvp/spec.md`.
- README, AGENTS.md, font guide, `.gitignore`.

## Langkah berikut (tasks.md)
1. `openspec init --tools opencode` lalu `graphify update .`.
2. Lengkapi `SurahMetadata` 114 surah + arti (file JSON, bukan hardcode 5).
3. Tambah DataStore persisten untuk bookmark/settings; Room bila daftar besar.
4. Bundel font KFGQPC + uji RTL di HP & tablet.
5. Build debug Android + run Desktop; iOS via Xcode.
