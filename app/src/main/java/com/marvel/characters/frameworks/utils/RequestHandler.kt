package com.marvel.characters.frameworks.utils

import com.marvel.characters.frameworks.dtos.ResultDto
import com.marvel.characters.presentation.utils.fromJson
import retrofit2.HttpException
import retrofit2.Response
import kotlin.Exception

suspend fun <T> request(block: suspend () -> Response<ResultDto<T>>): ResultDto<T>? {
    return try {
        val request = block()
        if (request.isSuccessful) {
            request.body()
        } else {
            request.errorBody()!!.string().fromJson()
        }
    } catch (e: Exception) {
        when (e) {
            is HttpException -> throw NetworkErrorException.parseException(e)
            else -> throw e
        }
    }
}