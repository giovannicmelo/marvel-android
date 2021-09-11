package com.marvel.characters.presentation.utils

import com.google.gson.Gson
import java.security.MessageDigest
import java.text.SimpleDateFormat
import java.util.Locale

val brLocale = Locale("pt", "BR")

// region JSON extensions
inline fun <reified T : Any> T.toJson(): String = Gson().toJson(this, T::class.java)
inline fun <reified T : Any> String.fromJson(): T = Gson().fromJson(this, T::class.java)
// endregion

// region String extensions
fun String.toMd5(): String {
    val md = MessageDigest.getInstance("MD5")
    val digested = md.digest(toByteArray())
    return digested.joinToString("") {
        String.format("%02x", it)
    }
}

fun String.toBrazilianDateFormat(inputPattern: String, outputPattern: String): String {
    var dateString = this
    val inputDateFormat = SimpleDateFormat(inputPattern, brLocale)
    val outputDateFormat = SimpleDateFormat(outputPattern, brLocale)
    try {
        inputDateFormat.parse(this)?.let { dateFormatted ->
            dateString = outputDateFormat.format(dateFormatted)
        }
    } catch (e: Exception) {
        e.printStackTrace()
    }
    return dateString
}

fun String?.toBrazilianDateTimeFormat(inputPattern: String = DataConstants.DATE_PATTERN_SERVER) =
    this?.toBrazilianDateFormat(
        inputPattern = inputPattern,
        outputPattern = DataConstants.DATE_PATTERN_APPLICATION
    ) ?: ""
// endregion