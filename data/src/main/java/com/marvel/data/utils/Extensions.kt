package com.marvel.data.utils

import com.google.gson.Gson
import java.security.MessageDigest

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
// endregion