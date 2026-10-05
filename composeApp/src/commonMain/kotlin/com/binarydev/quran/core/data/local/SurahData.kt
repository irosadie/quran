package com.binarydev.quran.core.data.local

/** Data 114 surah: nama latin (Indonesia), nama Arab, jumlah ayat, riwayat turun. */
object SurahData {
    val latin = listOf(
        "Al-Fatihah", "Al-Baqarah", "Ali 'Imran", "An-Nisa'", "Al-Ma'idah",
        "Al-An'am", "Al-A'raf", "Al-Anfal", "At-Taubah", "Yunus",
        "Hud", "Yusuf", "Ar-Ra'd", "Ibrahim", "Al-Hijr",
        "An-Nahl", "Al-Isra'", "Al-Kahf", "Maryam", "Taha",
        "Al-Anbiya'", "Al-Hajj", "Al-Mu'minun", "An-Nur", "Al-Furqan",
        "Asy-Syu'ara'", "An-Naml", "Al-Qasas", "Al-'Ankabut", "Ar-Rum",
        "Luqman", "As-Sajdah", "Al-Ahzab", "Saba'", "Fatir",
        "Yasin", "As-Saffat", "Sad", "Az-Zumar", "Gafir",
        "Fussilat", "Asy-Syura", "Az-Zukhruf", "Ad-Dukhan", "Al-Jasiyah",
        "Al-Ahqaf", "Muhammad", "Al-Fath", "Al-Hujurat", "Qaf",
        "Az-Zariyat", "At-Tur", "An-Najm", "Al-Qamar", "Ar-Rahman",
        "Al-Waqi'ah", "Al-Hadid", "Al-Mujadalah", "Al-Hasyr", "Al-Mumtahanah",
        "As-Saff", "Al-Jumu'ah", "Al-Munafiqun", "At-Tagabun", "At-Talaq",
        "At-Tahrim", "Al-Mulk", "Al-Qalam", "Al-Haqqah", "Al-Ma'arij",
        "Nuh", "Al-Jinn", "Al-Muzzammil", "Al-Muddassir", "Al-Qiyamah",
        "Al-Insan", "Al-Mursalat", "An-Naba'", "An-Nazi'at", "'Abasa",
        "At-Takwir", "Al-Infitar", "Al-Mutaffifin", "Al-Insyiqaq", "Al-Buruj",
        "At-Tariq", "Al-A'la", "Al-Gasyiyah", "Al-Fajr", "Al-Balad",
        "Asy-Syams", "Al-Lail", "Ad-Duha", "Asy-Syarh", "At-Tin",
        "Al-'Alaq", "Al-Qadr", "Al-Bayyinah", "Az-Zalzalah", "Al-'Adiyat",
        "Al-Qari'ah", "At-Takasur", "Al-'Asr", "Al-Humazah", "Al-Fil",
        "Quraisy", "Al-Ma'un", "Al-Kausar", "Al-Kafirun", "An-Nasr",
        "Al-Lahab", "Al-Ikhlas", "Al-Falaq", "An-Nas",
    )

    val arabic = listOf(
        "الفاتحة", "البقرة", "آل عمران", "النساء", "المائدة",
        "الأنعام", "الأعراف", "الأنفال", "التوبة", "يونس",
        "هود", "يوسف", "الرعد", "إبراهيم", "الحجر",
        "النحل", "الإسراء", "الكهف", "مريم", "طه",
        "الأنبياء", "الحج", "المؤمنون", "النور", "الفرقان",
        "الشعراء", "النمل", "القصص", "العنكبوت", "الروم",
        "لقمان", "السجدة", "الأحزاب", "سبأ", "فاطر",
        "يس", "الصافات", "ص", "الزمر", "غافر",
        "فصلت", "الشورى", "الزخرف", "الدخان", "الجاثية",
        "الأحقاف", "محمد", "الفتح", "الحجرات", "ق",
        "الذاريات", "الطور", "النجم", "القمر", "الرحمن",
        "الواقعة", "الحديد", "المجادلة", "الحشر", "الممتحنة",
        "الصف", "الجمعة", "المنافقون", "التغابن", "الطلاق",
        "التحريم", "الملك", "القلم", "الحاقة", "المعارج",
        "نوح", "الجن", "المزمل", "المدثر", "القيامة",
        "الإنسان", "المرسلات", "النبأ", "النازعات", "عبس",
        "التكوير", "الانفطار", "المطففين", "الانشقاق", "البروج",
        "الطارق", "الأعلى", "الغاشية", "الفجر", "البلد",
        "الشمس", "الليل", "الضحى", "الشرح", "التين",
        "العلق", "القدر", "البينة", "الزلزلة", "العاديات",
        "القارعة", "التكاثر", "العصر", "الهمزة", "الفيل",
        "قريش", "الماعون", "الكوثر", "الكافرون", "النصر",
        "المسد", "الإخلاص", "الفلق", "الناس",
    )

    val ayatCount = listOf(
        7, 286, 200, 176, 120,
        165, 206, 75, 129, 109,
        123, 111, 43, 52, 99,
        128, 111, 110, 98, 135,
        112, 78, 118, 64, 77,
        227, 93, 88, 69, 60,
        34, 30, 73, 54, 45,
        83, 182, 88, 75, 85,
        54, 53, 89, 59, 37,
        35, 38, 29, 18, 45,
        60, 49, 62, 55, 78,
        96, 29, 22, 24, 13,
        14, 11, 11, 18, 12,
        12, 30, 52, 52, 44,
        28, 28, 20, 56, 40,
        31, 50, 40, 46, 42,
        29, 19, 36, 25, 22,
        17, 19, 26, 30, 20,
        15, 21, 11, 8, 8,
        19, 5, 8, 8, 11,
        11, 8, 3, 9, 5,
        4, 7, 3, 6, 3,
        5, 4, 5, 6,
    )

    /** Surah-surah Madaniyah (daftar Kemenag); sisanya Makkiyah. */
    val madani = setOf(
        2, 3, 4, 5, 8, 9, 13, 22, 24, 33,
        47, 48, 49, 55, 57, 58, 59, 60, 61, 62,
        63, 64, 65, 66, 76, 98, 110,
    )

    init {
        require(latin.size == 114 && arabic.size == 114 && ayatCount.size == 114) {
            "Data surah harus 114: latin=${latin.size} arab=${arabic.size} ayat=${ayatCount.size}"
        }
    }
}
