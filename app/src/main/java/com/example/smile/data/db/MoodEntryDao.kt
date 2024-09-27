package com.example.smile.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.smile.data.entity.MoodEntryEntity
import com.example.smile.data.model.MoodColor
import java.time.LocalDate

@Dao
interface MoodEntryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMoodEntry(moodEntry: MoodEntryEntity)

    @Query("SELECT * FROM mood_entry WHERE date = :date")
    suspend fun getMoodEntryByDate(date: LocalDate): MoodEntryEntity?

    @Query("""
        SELECT date, mood_rating 
        FROM mood_entry 
        WHERE strftime('%Y', date) = :year 
        AND strftime('%m', date) = :month
    """)
    suspend fun getColorByMonth(year: String, month: String): List<MoodColor>
}