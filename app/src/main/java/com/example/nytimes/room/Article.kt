package com.example.nytimes.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Article(
    @PrimaryKey val id: String,
    val byline: String,
    val title: String,
    val url: String,
    val publishedDate: String,
    val thumbNail: String? = null,
)