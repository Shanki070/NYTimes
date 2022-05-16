package com.example.nytimes.screens.article

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.nytimes.BaseViewModel
import com.example.nytimes.network.NetworkResult
import com.example.nytimes.room.Article
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArticleViewModel @Inject internal constructor(private val articleRepository: ArticleRepository) : BaseViewModel() {

    private val _articles = MutableLiveData<List<Article>>()
    val articles: LiveData<List<Article>>
        get() = _articles

    fun fetchArticle() = viewModelScope.launch(Dispatchers.IO) {
        articleRepository.getAllArticlesFromDB()?.apply {
            _articles.postValue(this)
        }
        articleRepository.getArticlesFromServer().collect { response ->
            when (response) {
                is NetworkResult.Success -> {
                    response.data?.let { response ->
                        val arrayList = ArrayList<Article>()
                        response.results?.forEach { articles ->
                            val article = Article(
                                id = articles.id,
                                byline = articles.byline,
                                title = articles.title,
                                url = articles.url,
                                publishedDate = articles.publishedDate,
                                thumbNail =
                                if (articles.media?.isNotEmpty() == true && articles.media[0].metaData?.isNotEmpty() == true) {
                                    articles.media[0].metaData?.get(0)?.url
                                } else null,
                            )
                            arrayList.add(article)
                        }
                        arrayList.isNotEmpty().apply {
                            _articles.postValue(arrayList)
                            articleRepository.updateDB(arrayList)
                        }
                    }
                    _showProgressBar.postValue(false)
                }

                is NetworkResult.Error -> {
                    _showProgressBar.postValue(false)
                    _showSnackBarMessage.postValue(response.message)
                }

                is NetworkResult.Loading -> {
                    _showProgressBar.postValue(true)
                }
            }
        }
    }
}
