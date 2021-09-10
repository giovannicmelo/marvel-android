package com.marvel.data.remote.datasource

import com.marvel.data.BuildConfig
import com.marvel.data.contracts.CharacterDataSource
import com.marvel.data.dto.CharacterDTO
import com.marvel.data.remote.service.ApiService
import com.marvel.data.utils.getCurrentTimeStamp
import com.marvel.data.utils.toMd5
import retrofit2.Response

class CharacterRemoteDataSource(private val apiService: ApiService) :
    CharacterDataSource.Remote {

    private val timeStamp = getCurrentTimeStamp()
    private val digest = "$timeStamp${BuildConfig.PRIVATE_KEY}${BuildConfig.PUBLIC_KEY}"

    override suspend fun getAllAsync(filter: String?): Response<CharacterDTO> =
        if (filter.isNullOrEmpty()) {
            apiService.getCharactersAsync(
                BuildConfig.PUBLIC_KEY,
                digest.toMd5(),
                timeStamp
            )
        } else {
            apiService.getCharactersWithQueryAsync(
                BuildConfig.PUBLIC_KEY,
                digest.toMd5(),
                timeStamp,
                filter.toString()
            )
        }

    override suspend fun getByIdAsync(id: Int): Response<CharacterDTO> =
        apiService.getCharacterByIdAsync(
            id,
            BuildConfig.PUBLIC_KEY,
            digest.toMd5(),
            timeStamp
        )
}