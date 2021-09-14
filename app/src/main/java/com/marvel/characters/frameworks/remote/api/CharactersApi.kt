package com.marvel.characters.frameworks.remote.api

import com.marvel.characters.frameworks.dtos.CharacterDto
import com.marvel.characters.frameworks.dtos.ResultDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface CharactersApi {

    @GET("v1/public/characters")
    @Headers("Content-Type: application/json")
    suspend fun getCharacters(
        @Query("offset") offset: Int,
        @Query("apikey") apikey: String,
        @Query("hash") hash: String,
        @Query("ts") ts: String
    ): Response<ResultDto<CharacterDto>>

    @GET("v1/public/characters")
    @Headers("Content-Type: application/json")
    suspend fun getCharactersWithQuery(
        @Query("apikey") apikey: String,
        @Query("hash") hash: String,
        @Query("ts") ts: String,
        @Query("nameStartsWith") nameStartsWith: String
    ): Response<ResultDto<CharacterDto>>

    @GET("v1/public/characters/{characterId}")
    @Headers("Content-Type: application/json")
    suspend fun getCharacterById(
        @Path("characterId") characterId: Int,
        @Query("apikey") apikey: String,
        @Query("hash") hash: String,
        @Query("ts") ts: String
    ): Response<ResultDto<CharacterDto>>
}