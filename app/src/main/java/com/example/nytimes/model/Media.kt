package com.example.nytimes.model

import com.squareup.moshi.Json

data class Media(@Json(name = "media-metadata") val metaData: List<MediaMetadata>)