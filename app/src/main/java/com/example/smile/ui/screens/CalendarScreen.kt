package com.example.smile.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.smile.data.model.Day
import com.example.smile.ui.theme.MoodColors
import com.example.smile.ui.theme.MoodBorderColors
import com.example.smile.viewmodel.CalendarViewModel
import java.time.LocalDate

@Composable
fun CalendarScreen(navController: NavHostController, viewModel: CalendarViewModel) {
    val months by viewModel.months.collectAsState(emptyList())
    val currentYear by viewModel.currentYear.collectAsState(LocalDate.now().year)
    viewModel.refreshCalendar()

    Column {
        YearHeader(
            currentYear = currentYear,
            onPreviousYear = { viewModel.loadPreviousYear() },
            onNextYear = { viewModel.loadNextYear() }
        )

        LazyColumn {
            months.forEach { month ->
                item {
                    Text(
                        text = month.name,
                        style = MaterialTheme.typography.headlineMedium,
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )
                }
                item {
                    Column(modifier = Modifier.fillMaxWidth()) {
                        viewModel.getMonthFormatted(month).forEach { week ->
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.Start
                            ) {
                                week.forEach { day ->
                                    MoodSquare(
                                        day = day,
                                        onClick = {
                                            viewModel.checkMoodEntryExists(day.date) { exists ->
                                                if (exists) {
                                                    navController.navigate("moodEntryDisplay/${day.date}")
                                                } else {
                                                    navController.navigate("moodEntryForm/${day.date}")
                                                }
                                            }
                                        },
                                        modifier = Modifier.weight(1f)
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun YearHeader(
    currentYear: Int,
    onPreviousYear: () -> Unit,
    onNextYear: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        IconButton(onClick = onPreviousYear) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "Previous Year"
            )
        }
        Text(
            text = currentYear.toString(),
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.weight(1F),
            textAlign = TextAlign.Center
        )
        IconButton(onClick = onNextYear) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                contentDescription = "Next Year"
            )
        }
    }
}

@Composable
fun MoodSquare(
    day: Day,
    onClick: (Day) -> Unit,
    modifier: Modifier = Modifier
) {
    val borderColor = when (day.color) {
        MoodColors.Mood1Color -> MoodBorderColors.Mood1BorderColor
        MoodColors.Mood2Color -> MoodBorderColors.Mood2BorderColor
        MoodColors.Mood3Color -> MoodBorderColors.Mood3BorderColor
        MoodColors.Mood4Color -> MoodBorderColors.Mood4BorderColor
        MoodColors.Mood5Color -> MoodBorderColors.Mood5BorderColor
        else -> MoodBorderColors.DefaultBorderColor
    }

    Box(
        modifier = modifier
            .aspectRatio(1f)
            .then(
                if (day.color != Color.Transparent) {
                    Modifier.border(2.dp, borderColor)
                } else {
                    Modifier
                }
            )
            .background(color = day.color)
            .then(if (day.color != Color.Transparent) Modifier.clickable { onClick(day) } else Modifier),
        contentAlignment = Alignment.Center
    ) {
        if (day.color != Color.Transparent) {
            Text(
                text = day.date.dayOfMonth.toString(),
                color = Color.White,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

