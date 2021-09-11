package com.marvel.characters.frameworks.dtos

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ComicDto(
    val name: String? = null
) : Parcelable

@Parcelize
data class ComicListDto(
    val items: List<ComicDto>? = listOf()
) : Parcelable