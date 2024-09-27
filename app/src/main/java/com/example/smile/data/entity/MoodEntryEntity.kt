package com.example.smile.data.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate

@Entity(tableName = "mood_entry")
data class MoodEntryEntity(
    @PrimaryKey
    @ColumnInfo(name = "date")
    val date: LocalDate,

    @ColumnInfo(name = "mood_rating")
    val moodRating: Int,

    @ColumnInfo(name = "sleep_quality")
    val sleepQuality: Int,

    @ColumnInfo(name = "positives")
    val positives: String,

    @ColumnInfo(name = "negatives")
    val negatives: String,

    @ColumnInfo(name = "additional_details")
    val additionalDetails: String
)