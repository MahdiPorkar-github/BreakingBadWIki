package com.example.breakingbadwiki.data

data class ItemPost(
    val imageUrl: String,
    val txtTitle: String,
    val txtSubtitle: String,
    val txtDetail: String,

    // For trend fragment
    val isTrend: Boolean,
    val insight: String
)