package com.example.smile.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.smile.ui.screens.CalendarScreen
import com.example.smile.ui.screens.MoodEntryDisplayScreen
import com.example.smile.ui.screens.MoodEntryFormScreen
import com.example.smile.viewmodel.CalendarViewModel
import com.example.smile.viewmodel.MoodEntryViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import java.time.LocalDate

@Composable
fun AppNavGraph(
    navController: NavHostController,
    calendarViewModel: CalendarViewModel,
    moodEntryViewModel: MoodEntryViewModel
) {
    NavHost(navController = navController, startDestination = "calendar") {
        composable("calendar") { CalendarScreen(navController, calendarViewModel) }
        composable("moodEntryDisplay/{date}") { backStackEntry ->
            val dateString = backStackEntry.arguments?.getString("date") ?: return@composable
            val date = LocalDate.parse(dateString)
                MoodEntryDisplayScreen(
                    viewModel = moodEntryViewModel,
                    navController = navController,
                    date = date
                )
        }
        composable("moodEntryForm/{date}/{moodRating}/{sleepQuality}/{positives}/{negatives}/{additionalDetails}") { backStackEntry ->
            val dateString = backStackEntry.arguments?.getString("date") ?: return@composable
            val date = LocalDate.parse(dateString)

            val moodRatingString = backStackEntry.arguments?.getString("moodRating") ?: "3"
            val moodRating = moodRatingString.toInt()

            val sleepQualityString = backStackEntry.arguments?.getString("sleepQuality") ?: "3"
            val sleepQuality = sleepQualityString.toInt()

            val positives = backStackEntry.arguments?.getString("positives") ?: ""
            val negatives = backStackEntry.arguments?.getString("negatives") ?: ""
            val additionalDetails = backStackEntry.arguments?.getString("additionalDetails") ?: ""

            MoodEntryFormScreen(
                date = date,
                moodRating = MutableStateFlow<Int?>(moodRating),
                sleepQuality = MutableStateFlow<Int?>(sleepQuality),
                positives = MutableStateFlow<String?>(positives),
                negatives = MutableStateFlow<String?>(negatives),
                additionalDetails = MutableStateFlow<String?>(additionalDetails),
                onSubmit = { moodEntry ->
                    moodEntryViewModel.saveMoodEntry(moodEntry)
                    navController.navigate("calendar")
                },
                onCancel = {
                    navController.popBackStack()
                }
            )
        }
        composable("moodEntryForm/{date}") { backStackEntry ->
            val dateString = backStackEntry.arguments?.getString("date") ?: return@composable
            val date = LocalDate.parse(dateString)

            MoodEntryFormScreen(
                date = date,
                moodRating = MutableStateFlow<Int?>(null),
                sleepQuality = MutableStateFlow<Int?>(null),
                positives = MutableStateFlow<String?>(null),
                negatives = MutableStateFlow<String?>(null),
                additionalDetails = MutableStateFlow<String?>(null),
                onSubmit = { moodEntry ->
                    moodEntryViewModel.saveMoodEntry(moodEntry)
                    navController.navigate("calendar")
                },
                onCancel = {
                    navController.popBackStack()
                }
            )
        }
    }
}