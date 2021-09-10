package com.marvel.characters.base

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.marvel.data.enums.ApiResponseCode
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlin.coroutines.CoroutineContext

abstract class BaseViewModel : ViewModel(), CoroutineScope {

    private val viewModelExceptionHandler =
        CoroutineExceptionHandler { coroutineContext, throwable ->
            Log.d(
                "CoroutineException",
                "coroutineContext: $coroutineContext throwable: ${throwable.printStackTrace()}"
            )
        }

    private val job = Job()
    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.IO + viewModelExceptionHandler

    private val _apiErrorLiveData = MutableLiveData<Pair<ApiResponseCode, String?>>()
    val apiErrorLiveData = Transformations.map(_apiErrorLiveData) { it }

    fun setApiError(code: Int, message: String?) {
        val apiErrorCode: ApiResponseCode = when (code) {
            400 -> ApiResponseCode.BAD_REQUEST
            401 -> ApiResponseCode.UNAUTHORIZED
            403 -> ApiResponseCode.FORBIDDEN
            409 -> ApiResponseCode.CONFLICT
            404 -> ApiResponseCode.NOT_FOUND
            500 -> ApiResponseCode.INTERNAL_SERVER_ERROR
            503 -> ApiResponseCode.SERVICE_UNAVAILABLE
            else -> ApiResponseCode.BAD_REQUEST
        }
        _apiErrorLiveData.postValue(Pair(apiErrorCode, message))
    }

    override fun onCleared() {
        coroutineContext.cancel()
        super.onCleared()
    }
}