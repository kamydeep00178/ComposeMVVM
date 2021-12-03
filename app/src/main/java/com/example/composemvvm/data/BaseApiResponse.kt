package com.example.composemvvm.data

import retrofit2.Response

abstract class BaseApiResponse {
    suspend fun <T> safeApiCall(apiCall: suspend () -> Response<T>):
            ResultState<T> {
        try {
            val response = apiCall()
            if (response.isSuccessful) {
                val body = response.body()
                body?.let {
                    return ResultState.Success(body)
                }
            }
            return error("${response.code()} ${response.message()}")
        } catch (exc: Exception) {
            return error(exc.message ?: exc.toString())
        }
    }

    private fun <T> error(errorMessage: String): ResultState<T> =
        ResultState.Error("Api call failed $errorMessage")
}