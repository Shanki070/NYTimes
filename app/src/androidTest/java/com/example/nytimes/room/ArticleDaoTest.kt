package com.example.nytimes.room

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
open class  ArticleDaoTest : DatabaseTest() {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Test
    fun insertAllTest() = runBlocking {
        val article1 = Article("1", "Author 1", "Title 1", "test url 1", "27/12/1998")
        val article2 = Article("2", "Author 2", "Title 2", "test url 2", "27/12/1998")
        appDatabase.articleDao().insertAll(listOf(article1, article2).toList())
        val articleSize = appDatabase.articleDao().getAllArticles()?.size
        assertEquals(articleSize, 2)
    }

    @Test
    fun deleteAllTest() = runBlocking {
        val article1 = Article("1", "Author 1", "Title 1", "test url 1", "27/12/1998")
        val article2 = Article("2", "Author 2", "Title 2", "test url 2", "27/12/1998")
        appDatabase.articleDao().insertAll(listOf(article1, article2).toList())
        appDatabase.articleDao().deleteAll()
        val articleSize = appDatabase.articleDao().getAllArticles()?.size
        assertEquals(articleSize, 0)
    }

    @Test
    fun getAllArticlesTest() = runBlocking {
        val article1 = Article("1", "Author 1", "Title 1", "test url 1", "27/12/1998")
        appDatabase.articleDao().insertAll(listOf(article1).toList())
        val articleSize = appDatabase.articleDao().getAllArticles()?.size
        assertEquals(articleSize, 1)
    }
}
