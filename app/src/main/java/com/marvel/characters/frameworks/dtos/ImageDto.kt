package com.marvel.characters.frameworks.dtos

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ImageDto(
    val path: String? = null,
    val extension: String? = null
) : Parcelable