package com.binarydev.quran.core.data.audio

/** Qari dari everyayah.com (gratis, tanpa auth, MP3 per ayat). Terverifikasi HTTP 200. */
data class Reciter(val name: String, val short: String, val dir: String)

object Reciters {
    val alafasy = Reciter("Misyari Rasyid", "Misyari", "Alafasy_128kbps")
    val husary = Reciter("Mahmud Al-Husari", "Husari", "Husary_128kbps")
    val minshawi = Reciter("M. S. Al-Minsyawi", "Minsyawi", "Minshawy_Murattal_128kbps")

    val all = listOf(alafasy, husary, minshawi)
    val default: Reciter = alafasy
}

/** URL audio satu ayat: https://everyayah.com/data/{qari}/{SSSAAA}.mp3 */
fun audioUrl(reciter: Reciter, surah: Int, ayah: Int): String {
    val s = surah.toString().padStart(3, '0')
    val a = ayah.toString().padStart(3, '0')
    return "https://everyayah.com/data/${reciter.dir}/$s$a.mp3"
}
