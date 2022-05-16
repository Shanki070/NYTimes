package com.example.nytimes.model

import com.squareup.moshi.Json

data class ArticleResponse(
    val id: String,
    val byline: String,
    val title: String,
    val url: String,
    @Json(name = "published_date") val publishedDate: String,
    val media: List<Media>
)