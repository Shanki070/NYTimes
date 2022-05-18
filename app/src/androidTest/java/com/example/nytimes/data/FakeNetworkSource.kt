package com.example.nytimes.data

import com.example.nytimes.model.ArticleResponse
import com.example.nytimes.model.MostPopularResponse
import com.example.nytimes.network.MostPopularApiService
import retrofit2.Response

class FakeNetworkSource: MostPopularApiService {

    override suspend fun getArticles(appId: String): Response<MostPopularResponse> {
        val list = listOf<ArticleResponse>(ArticleResponse("1", "author", "title",
            "url", "02-04-2022", emptyList()))
        return Response.success(MostPopularResponse(list))
    }
}