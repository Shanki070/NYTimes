package com.example.nytimes.screens.article

import com.example.nytimes.model.MostPopularResponse
import com.example.nytimes.network.MostPopularApiService
import com.example.nytimes.network.NetworkHelper
import com.example.nytimes.network.NetworkResult
import com.example.nytimes.room.Article
import com.example.nytimes.room.ArticleDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ArticleRepository @Inject constructor(private val articleDao: ArticleDao,
                                            private val apiService: MostPopularApiService) {

    fun getAllArticlesFromDB(): List<Article>? {
        return articleDao.getAllArticles()
    }

    suspend fun updateDB(articles: List<Article>) {
        articleDao.updateArticles(articles)
    }

    suspend fun getArticlesFromServer(): Flow<NetworkResult<MostPopularResponse>> {
        return flow {
            emit(NetworkResult.Loading())
            emit(NetworkHelper.convertApiCall { apiService.getArticles() })
        }.flowOn(Dispatchers.IO)
    }
}
