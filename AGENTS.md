# AGENTS.md — cara kerja AI di repo ini

## Perintah cepat
- `./gradlew :composeApp:assembleDebug` — build Android (butuh `JAVA_HOME=/opt/homebrew/opt/openjdk@17`)
- `rtk git status` / `rtk git diff` — status/diff hemat token (jangan `git status` mentah)
- `rtk read <file>` — baca file hemat token untuk file besar
- `openspec` — lihat `openspec/specs/quran-mvp/spec.md` sebelum tambah fitur
- `graphify query "<pertanyaan>"` — tanya arsitektur via knowledge graph

## Aturan arsitektur (wajib)
1. **MVVM + UDF**: tiap layar = `*Contract.kt` (State/Event/Effect) + `*ViewModel.kt` (satu StateFlow, fun onEvent) + `*Screen.kt` (stateless, collectAsStateWithLifecycle).
2. **Satu arah**: Screen → `onEvent` → ViewModel → `state.update` / `_effect.send`. Jangan panggil repo dari Screen.
3. **Ringan**: lazy loading per surah/halaman, cache memori dulu, jangan preload 604 halaman. Matikan Ktor Logging di rilis.
4. **Responsif**: pakai `rememberScreenClass()` + `NavigationSuiteScaffold`; uji compact (HP) & expanded (tablet/desktop).
5. **Mushaf**: teks Arab selalu `text_uthmani`, RTL, hormati `fontScale`; halaman selalu 1..604 (`coerceIn`).
6. **Bahasa**: komentar & UI Indonesia; log error ringkas.

## Alur spec (OpenSpec)
- Fitur baru: `openspec/specs/<nama>/spec.md` → `openspec/changes/<nama>/proposal.md,tasks.md` → implementasi → arsip.
- Jangan ubah API `QuranApi` tanpa update spec `quran-mvp`.

## MCP
- Serena: edit via simbol (`find_symbol`, `replace_symbol_body`) untuk file Kotlin, bukan rewrite penuh.
- Headroom: `compress` output besar sebelum reasoning; `retrieve` bila butuh detail.
- Graphify: tiap perubahan arsitektur besar → `graphify update .` agar graph tetap segar.
- Quran MCP (`quran-api`): cek teks ayat/tafsir saat validasi data, bukan untuk build.
