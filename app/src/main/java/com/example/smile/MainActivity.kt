package com.example.smile

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.smile.navigation.AppNavGraph
import com.example.smile.ui.theme.SmileAppTheme
import com.example.smile.viewmodel.CalendarViewModel
import com.example.smile.viewmodel.MoodEntryViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SmileAppTheme {
                SmileApp()
            }
        }
    }
}

@Composable
fun SmileApp() {
    val navController = rememberNavController()
    val calendarViewModel: CalendarViewModel = viewModel(factory = CalendarViewModel.factory )
    val moodEntryViewModel: MoodEntryViewModel = viewModel(factory = MoodEntryViewModel.factory)

    Surface {
        AppNavGraph(
            navController = navController,
            calendarViewModel = calendarViewModel,
            moodEntryViewModel = moodEntryViewModel
        )
    }
}