package com.marvel.characters.frameworks.remote.services

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.marvel.characters.BuildConfig
import com.marvel.characters.BuildConfig.BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ServiceClient {

    private const val TIMEOUT: Long = 20

    fun <T> getClient(api: Class<T>): T {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(createOkHttpClient())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(api)
    }

    private fun logInterceptor(level: HttpLoggingInterceptor.Level): HttpLoggingInterceptor = if (BuildConfig.DEBUG)
        HttpLoggingInterceptor().also { it.level = level }
    else
        HttpLoggingInterceptor().also { it.level = HttpLoggingInterceptor.Level.NONE }

    private fun createOkHttpClient(): OkHttpClient {
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(logInterceptor(HttpLoggingInterceptor.Level.BODY))
            .addInterceptor(logInterceptor(HttpLoggingInterceptor.Level.BASIC))
            .readTimeout(TIMEOUT, TimeUnit.SECONDS)
            .connectTimeout(TIMEOUT, TimeUnit.SECONDS)

        return okHttpClient.build()
    }
}