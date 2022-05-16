package com.example.nytimes.room

import androidx.room.*

@Dao
interface ArticleDao {

    @Query("SELECT * FROM article")
    fun getAllArticles(): List<Article>?

    @Query("DELETE FROM article")
    fun deleteAll()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(articles: List<Article>)

    @Transaction
    suspend fun updateArticles(articles: List<Article>) {
        deleteAll()
        insertAll(articles)
    }
}