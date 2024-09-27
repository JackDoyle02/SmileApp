package com.example.smile.data.model

import androidx.room.ColumnInfo
import java.time.LocalDate

data class MoodColor(
    val date: LocalDate,
    @ColumnInfo(name = "mood_rating")
    val moodRating: Int
)
