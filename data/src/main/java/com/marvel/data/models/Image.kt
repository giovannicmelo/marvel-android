package com.marvel.data.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Image(
    val path: String? = null,
    val extension: String? = null
) : Parcelable