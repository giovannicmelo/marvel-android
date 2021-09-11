package com.marvel.characters.frameworks.dtos

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CharacterDto(
    val id: Int? = null,
    val name: String? = null,
    val description: String? = null,
    val modified: String? = null,
    val thumbnail: ImageDto? = null,
    val resourceURI: String? = null,
    val comics: ComicListDto? = null
) : Parcelable