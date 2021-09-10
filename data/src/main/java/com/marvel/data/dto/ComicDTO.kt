package com.marvel.data.dto

import android.os.Parcelable
import com.marvel.data.models.Comic
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ComicDTO(
    val available: Int? = null,
    val items: List<Comic>? = listOf()
) : Parcelable