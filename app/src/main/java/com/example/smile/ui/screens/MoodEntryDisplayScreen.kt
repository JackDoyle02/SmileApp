package com.example.smile.ui.screens

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.smile.viewmodel.MoodEntryViewModel
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Composable
fun MoodEntryDisplayScreen(
    navController: NavHostController,
    viewModel: MoodEntryViewModel,
    date: LocalDate,
    modifier: Modifier = Modifier
) {
    val moodEntry by viewModel.moodEntry.collectAsState(initial = null)
    viewModel.getMoodEntry(date)

    val formatter = DateTimeFormatter.ofPattern("d MMM yyyy")

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Mood Entry Details",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        moodEntry?.let { entry ->
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .border(1.dp, MaterialTheme.colorScheme.onSurface.copy(alpha = 0.12f), shape = MaterialTheme.shapes.medium)
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                val formattedDate = entry.date.format(formatter)

                Text(
                    text = formattedDate,
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp)
                        .wrapContentWidth(Alignment.CenterHorizontally)
                )
                Row(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = "Mood Rating: ",
                        style = MaterialTheme.typography.bodyLarge.copy(
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.primary
                        )
                    )
                    Text(
                        text = "${entry.moodRating}",
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
                Row(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = "Sleep Quality: ",
                        style = MaterialTheme.typography.bodyLarge.copy(
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.primary
                        )
                    )
                    Text(
                        text = "${entry.sleepQuality}",
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
                Text(
                    text = "Positives:",
                    style = MaterialTheme.typography.bodyLarge.copy(
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary
                    ),
                    modifier = Modifier.padding(top = 12.dp)
                )
                Text(
                    text = entry.positives,
                    style = MaterialTheme.typography.bodyLarge
                )
                Text(
                    text = "Negatives:",
                    style = MaterialTheme.typography.bodyLarge.copy(
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary
                    ),
                    modifier = Modifier.padding(top = 12.dp)
                )
                Text(
                    text = entry.negatives,
                    style = MaterialTheme.typography.bodyLarge
                )
                Text(
                    text = "Additional Details:",
                    style = MaterialTheme.typography.bodyLarge.copy(
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary
                    ),
                    modifier = Modifier.padding(top = 12.dp)
                )
                Text(
                    text = entry.additionalDetails,
                    style = MaterialTheme.typography.bodyLarge
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 24.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(onClick = { navController.popBackStack() }) {
                    Text("Back")
                }
                Button(onClick = {
                    navController.navigate("moodEntryForm/${entry.date}/${entry.moodRating}/${entry.sleepQuality}/${entry.positives}/${entry.negatives}/${entry.additionalDetails}")
                }) {
                    Text("Edit")
                }
            }
        }
    }
}


