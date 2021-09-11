package com.marvel.characters.frameworks.dtos

data class ResultDto<T>(
    val code: Int,
    val status: String,
    val data: ResultDataDto<T>? = null
)

data class ResultDataDto<T>(
    val offset: Int? = null,
    val limit: Int? = null,
    val total: Int? = null,
    val count: Int? = null,
    val results: List<T>? = listOf()
)