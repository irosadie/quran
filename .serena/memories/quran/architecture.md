# Quran KMP — arsitektur MVVM+UDF

- Root: `composeApp/src/commonMain/kotlin/com/binarydev/quran/`
- `app/`: `QuranApp.kt` (NavigationSuiteScaffold adaptif + NavHost type-safe), `Di.kt` (Koin `appModule`)
- `core/common/`: `MviContract.kt` (State/Event/Effect), `AppResult.kt` (Ok/Err/Loading)
- `core/designsystem/`: `Theme.kt`, `MushafText.kt` (RTL Utsmani, fontScale), `Responsive.kt` (`rememberScreenClass()`)
- `core/domain/model/`: Surah, Ayah (`textUthmani`, page 1..604), MushafPage, Bookmark
- `core/domain/repository/`, `core/domain/usecase/` (GetMushafPageUseCase coerceIn 1..604, SearchAyahUseCase min 2 huruf + debounce di VM)
- `core/data/remote/api/QuranApi.kt`: Quran Foundation v4, `mushaf=1` (QCF V2 Madinah); expect/actual `engine()` per platform
- `core/data/repository/QuranRepositoryImpl.kt`: cache memori page+surah, offline-first
- `feature/{home,reader,search,bookmark,settings}/presentation/`: `*Contract.kt` + `*ViewModel.kt` (satu StateFlow, `onEvent`, Channel Effect) + `*Screen.kt` (collectAsStateWithLifecycle)
- Aturan: edit Kotlin via simbol (find_symbol/replace_symbol_body); jangan panggil repo dari Screen; Ktor Logging mati di rilis.
- Build: `JAVA_HOME=/opt/homebrew/opt/openjdk@17 ./gradlew :composeApp:assembleDebug`
