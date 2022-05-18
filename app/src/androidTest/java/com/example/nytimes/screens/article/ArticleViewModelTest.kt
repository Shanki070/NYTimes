package com.example.nytimes.screens.article

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.matcher.ViewMatchers.assertThat
import com.example.nytimes.data.FakeNetworkSource
import com.example.nytimes.getOrAwaitValue
import com.example.nytimes.room.AppDatabase
import com.example.nytimes.room.Article
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers.not
import org.hamcrest.CoreMatchers.nullValue
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class ArticleViewModelTest {

    // Subject under test
    private lateinit var viewModel: ArticleViewModel

    private lateinit var articleRepository: ArticleRepository

    private lateinit var appDatabase: AppDatabase

    // Executes each task synchronously using Architecture Components.
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setupViewModel() = runBlocking {
        // We initialise the tasks to 3, with one active and two completed
        appDatabase = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AppDatabase::class.java)
            .allowMainThreadQueries()
            .build()
        val article1 = Article("1", "Author 1", "Title 1", "test url 1", "27/12/1998")
        val article2 = Article("2", "Author 2", "Title 2", "test url 2", "27/12/1998")
        appDatabase.articleDao().insertAll(listOf(article1, article2).toList())
        articleRepository = ArticleRepository(appDatabase.articleDao(), FakeNetworkSource())
        viewModel = ArticleViewModel(articleRepository)
    }

    @Test
    fun verifyChangesInArticle() {
        // When fetching the articles from server
        viewModel.fetchArticle()

        // Then observing the value in the liveData object
        val value = viewModel.articles.getOrAwaitValue()

        assertThat(value, not(nullValue()))
    }

}