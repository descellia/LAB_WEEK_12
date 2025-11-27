package com.example.test_lab_week_12.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movie(
    val id: Int,
    val title: String,
    val posterPath: String?,
    val releaseDate: String?,
    val popularity: Double
) : Parcelable