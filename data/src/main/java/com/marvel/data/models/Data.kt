package com.marvel.data.models

data class Data(
    val offset: Int? = null,
    val limit: Int? = null,
    val total: Int? = null,
    val count: Int? = null,
    val results: List<Character>? = listOf()
)