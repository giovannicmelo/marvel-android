package com.marvel.characters.presentation.utils

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.marvel.characters.frameworks.utils.ExceptionHandler
import com.marvel.characters.frameworks.utils.State

fun <T> state(block: suspend () -> T): LiveData<State<T>> {
    return liveData {
        emit(State.LoadingState)
        try {
            emit(State.DataState(block()))
        } catch (e: Exception) {
            e.printStackTrace()
            emit(ExceptionHandler.resolveError(e))
        }
    }
}