package com.example.nytimes.network

import retrofit2.Response

object NetworkHelper {

    suspend fun <T> convertApiCall(apiCall: suspend () -> Response<T>): NetworkResult<T> {
        try {
            val response = apiCall()
            if (response.isSuccessful) {
                val body = response.body()
                body?.let {
                    return NetworkResult.Success(body)
                }
            }
            return NetworkResult.Error("Api failed ${response.code()} ${response.message()}")
        } catch (e: Exception) {
            return NetworkResult.Error("Api failed ${e.message ?: e.toString()}")
        }
    }
}