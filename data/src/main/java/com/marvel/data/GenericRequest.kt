package com.marvel.data

import org.json.JSONObject
import retrofit2.Response

suspend fun <T> request(requestBlock: suspend () -> Response<T>): RepositoryObject<T> {
    try {
        val getRequest = requestBlock()

        return if (getRequest.isSuccessful) {
            RepositoryObject(
                remote = DataSourceResponse(code = getRequest.code(), message = MESSAGE_SUCCESS),
                content = getRequest.body()
            )
        } else {
            var message = ""
            getRequest.errorBody()?.string()?.run {
                val errorJson = JSONObject(this)
                message = errorJson.getString("message")
            }

            RepositoryObject(
                remote = DataSourceResponse(
                    getRequest.code(),
                    message
                ),
                content = getRequest.body()
            )
        }
    } catch (e: Exception) {
        e.printStackTrace()
        return RepositoryObject(
            remote = DataSourceResponse(code = REMOTE_ERROR, message = MESSAGE_ERROR)
        )
    }
}