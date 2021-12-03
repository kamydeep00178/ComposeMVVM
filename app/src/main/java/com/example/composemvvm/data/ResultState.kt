package com.example.composemvvm.data

sealed class ResultState<out T> {
    data class Success<T>(val data: T) : ResultState<T>()

    data class Error(
        val message: String?
    ) : ResultState<Nothing>()

    class Loading<T> : ResultState<T>()
}