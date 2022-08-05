package com.example.breakingbadwiki.data

import kotlinx.parcelize.Parcelize
import android.os.Parcelable

@Parcelize
data class ItemPost(
    val imageUrl: String,
    val txtTitle: String,
    val txtSubtitle: String,
    val txtDetail: String,

    // For trend fragment
    val isTrend: Boolean,
    val insight: String
) : Parcelable