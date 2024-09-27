package com.example.smile.data.model

import java.time.LocalDate

data class MoodEntry(
    val date: LocalDate,
    val moodRating: Int,
    val sleepQuality: Int,
    val positives: String,
    val negatives: String,
    val additionalDetails: String
)
