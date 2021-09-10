package com.marvel.core.characters.domain

data class Character(
    val id: Int? = null,
    val name: String? = null,
    val description: String? = null,
    val modified: String? = null,
    val thumbnail: Image? = null,
    val resourceURI: String? = null,
    val comics: List<Comic>? = listOf()
)