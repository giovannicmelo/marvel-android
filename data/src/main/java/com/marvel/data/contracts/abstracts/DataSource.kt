package com.marvel.data.contracts.abstracts

import retrofit2.Response

interface DataSource {

    interface Remote<T> {
        suspend fun getAllAsync(filter: String? = null): Response<T>
        suspend fun getByIdAsync(id: Int): Response<T>
    }
}