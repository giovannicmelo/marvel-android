package com.marvel.data.models

import android.os.Parcelable
import com.marvel.data.dto.ComicDTO
import com.marvel.data.utils.DataConstants.DATE_PATTERN_APPLICATION
import com.marvel.data.utils.DataConstants.DATE_PATTERN_SERVER
import kotlinx.android.parcel.Parcelize
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

@Parcelize
data class Character(
    val id: Int? = null,
    val name: String? = null,
    val description: String? = null,
    val modified: String? = null,
    val thumbnail: Image? = null,
    val resourceURI: String? = null,
    val comics: ComicDTO? = null
) : Parcelable {
    fun dateFormatted(): String? {
        modified?.run {
            val cal = Calendar.getInstance().also {
                it.time = SimpleDateFormat(
                    DATE_PATTERN_SERVER,
                    Locale.getDefault()
                ).parse(this) ?: Date()
            }

            return SimpleDateFormat(
                DATE_PATTERN_APPLICATION, Locale.getDefault()
            ).format(cal.time)
        }
        return modified
    }
}