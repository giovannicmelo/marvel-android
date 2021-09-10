package com.marvel.data.remote.service

import com.marvel.data.dto.CharacterDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("v1/public/characters")
    @Headers("Content-Type: application/json")
    suspend fun getCharactersAsync(
        @Query("apikey") apikey: String,
        @Query("hash") hash: String,
        @Query("ts") ts: String
    ): Response<CharacterDTO>

    @GET("v1/public/characters")
    @Headers("Content-Type: application/json")
    suspend fun getCharactersWithQueryAsync(
        @Query("apikey") apikey: String,
        @Query("hash") hash: String,
        @Query("ts") ts: String,
        @Query("nameStartsWith") nameStartsWith: String
    ): Response<CharacterDTO>

    @GET("v1/public/characters/{characterId}")
    @Headers("Content-Type: application/json")
    suspend fun getCharacterByIdAsync(
        @Path("characterId") characterId: Int,
        @Query("apikey") apikey: String,
        @Query("hash") hash: String,
        @Query("ts") ts: String
    ): Response<CharacterDTO>
}