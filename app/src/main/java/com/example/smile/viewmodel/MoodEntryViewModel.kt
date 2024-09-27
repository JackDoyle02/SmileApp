package com.example.smile.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.smile.SmileApplication
import com.example.smile.data.db.MoodEntryDao
import com.example.smile.data.entity.MoodEntryEntity
import com.example.smile.data.model.MoodEntry
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.time.LocalDate

class MoodEntryViewModel(private val moodEntryDao: MoodEntryDao) : ViewModel() {
    private val _moodEntry = MutableStateFlow<MoodEntry?>(null)
    val moodEntry: StateFlow<MoodEntry?> get() = _moodEntry

    private fun convertMoodEntryToEntity(moodEntry: MoodEntry): MoodEntryEntity {
        return MoodEntryEntity(
            date = moodEntry.date,
            moodRating = moodEntry.moodRating,
            sleepQuality = moodEntry.sleepQuality,
            positives = moodEntry.positives,
            negatives = moodEntry.negatives,
            additionalDetails = moodEntry.additionalDetails
        )
    }

    private fun convertMoodEntityToEntry(moodEntryEntity: MoodEntryEntity): MoodEntry {
        return MoodEntry(
            date = moodEntryEntity.date,
            moodRating = moodEntryEntity.moodRating,
            sleepQuality = moodEntryEntity.sleepQuality,
            positives = moodEntryEntity.positives,
            negatives = moodEntryEntity.negatives,
            additionalDetails = moodEntryEntity.additionalDetails
        )
    }

    fun saveMoodEntry(moodEntry: MoodEntry) {
        viewModelScope.launch {
            val moodEntryEntity = convertMoodEntryToEntity(moodEntry)
            moodEntryDao.insertMoodEntry(moodEntryEntity)
        }
    }

    fun getMoodEntry(date: LocalDate) {
        viewModelScope.launch {
            val moodEntryEntity = moodEntryDao.getMoodEntryByDate(date)
            _moodEntry.value = moodEntryEntity?.let { convertMoodEntityToEntry(it) }
        }
    }

    companion object {
        val factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as SmileApplication)
                MoodEntryViewModel(application.database.moodEntryDao())
            }
        }
    }
}
