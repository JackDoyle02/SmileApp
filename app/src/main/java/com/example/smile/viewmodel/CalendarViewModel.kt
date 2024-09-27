package com.example.smile.viewmodel

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.smile.SmileApplication
import com.example.smile.data.db.MoodEntryDao
import com.example.smile.data.model.Day
import com.example.smile.data.model.Month
import com.example.smile.data.model.MoodColor
import com.example.smile.ui.theme.MoodColors
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.time.LocalDate


class CalendarViewModel(private val moodEntryDao: MoodEntryDao) : ViewModel() {
    private val _months = MutableStateFlow<List<Month>>(emptyList())
    val months: StateFlow<List<Month>> get() = _months

    private val _currentYear = MutableStateFlow(LocalDate.now().year)
    val currentYear: StateFlow<Int> get() = _currentYear

    init {
        loadCurrentYear()
    }

    private fun loadCurrentYear() {
        val year = _currentYear.value

        viewModelScope.launch {
            val monthList = mutableListOf<Month>()

            for (month in 1..12) {
                val moodColors = moodEntryDao.getColorByMonth(year.toString(), month.toString().padStart(2, '0'))

                monthList.add(Month(
                    name = LocalDate.of(year, month, 1).month.name,
                    days = getAllDaysOfMonth(year, month, moodColors)
                ))
            }

            _months.value = monthList
        }
    }

    private fun getAllDaysOfMonth(year: Int, month: Int, moodColors: List<MoodColor>): List<Day> {
        val moodColorMap = mapOf(
            1 to MoodColors.Mood1Color,
            2 to MoodColors.Mood2Color,
            3 to MoodColors.Mood3Color,
            4 to MoodColors.Mood4Color,
            5 to MoodColors.Mood5Color
        )

        val daysInMonth = LocalDate.of(year, month, 1).lengthOfMonth()

        return (1..daysInMonth).map { day ->
            val date = LocalDate.of(year, month, day)
            val moodColor = moodColors.find { it.date == date }
            val color = moodColor?.let { moodColorMap[it.moodRating] } ?: MoodColors.DefaultMoodColor

            Day(date = date, color = color)
        }
    }

    fun refreshCalendar() {
        loadCurrentYear()
    }

    fun loadPreviousYear() {
        _currentYear.value -= 1
        loadCurrentYear()
    }

    fun loadNextYear() {
        _currentYear.value += 1
        loadCurrentYear()
    }

    fun getMonthFormatted(month: Month): List<List<Day>> {
        val firstDayOfMonth = month.days.first().date
        val startDay = firstDayOfMonth.dayOfWeek.value

        val emptyDays = List(startDay - 1) { Day(date = LocalDate.now(), color = Color.Transparent) }
        val allDays = (emptyDays + month.days).toMutableList()
        val additionalEmptyDays = List(7 - (allDays.size % 7)) { Day(date = LocalDate.now(), color = Color.Transparent) }
        allDays += additionalEmptyDays

        return allDays.chunked(7)
    }

    fun checkMoodEntryExists(date: LocalDate, callback: (Boolean) -> Unit) {
        viewModelScope.launch {
            val exists = moodEntryDao.getMoodEntryByDate(date) != null
            callback(exists)
        }
    }

    companion object {
        val factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as SmileApplication)
                CalendarViewModel(application.database.moodEntryDao())
            }
        }
    }
}
