package com.example.nytimes.network

import com.example.nytimes.model.MostPopularResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MostPopularApiService {
    @GET("/svc/mostpopular/v2/viewed/7.json")
    suspend fun getArticles(@Query("api-key") appId: String = NetworkConstant.API_KEY): Response<MostPopularResponse>
}