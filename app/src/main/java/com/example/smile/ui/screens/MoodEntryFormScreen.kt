package com.example.smile.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.example.smile.data.model.MoodEntry
import kotlinx.coroutines.flow.StateFlow
import java.time.LocalDate

@Composable
fun MoodEntryFormScreen(
    date: LocalDate,
    moodRating: StateFlow<Int?>,
    sleepQuality: StateFlow<Int?>,
    positives: StateFlow<String?>,
    negatives: StateFlow<String?>,
    additionalDetails: StateFlow<String?>,
    onSubmit: (MoodEntry) -> Unit,
    onCancel: () -> Unit,
    modifier: Modifier = Modifier
) {
    val moodRatingState by moodRating.collectAsState()
    var moodRatingValue by remember { mutableIntStateOf(moodRatingState ?: 3) }

    val sleepQualityState by sleepQuality.collectAsState()
    var sleepQualityValue by remember { mutableIntStateOf(sleepQualityState ?: 3) }

    val positivesState by positives.collectAsState()
    var positivesValue by remember { mutableStateOf(TextFieldValue(positivesState ?: "")) }

    val negativesState by negatives.collectAsState()
    var negativesValue by remember { mutableStateOf(TextFieldValue(negativesState ?: "")) }

    val additionalDetailsState by additionalDetails.collectAsState()
    var additionalDetailsValue by remember { mutableStateOf(TextFieldValue(additionalDetailsState ?: "")) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Mood Entry",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Text("Mood Rating", modifier = Modifier.padding(vertical = 8.dp))
        Slider(
            value = moodRatingValue.toFloat(),
            onValueChange = { moodRatingValue = it.toInt() },
            valueRange = 1f..5f,
            steps = 3,
            modifier = Modifier.fillMaxWidth()
        )

        Text("Sleep Quality", modifier = Modifier.padding(vertical = 8.dp))
        Slider(
            value = sleepQualityValue.toFloat(),
            onValueChange = { sleepQualityValue = it.toInt() },
            valueRange = 1f..5f,
            steps = 3,
            modifier = Modifier.fillMaxWidth()
        )

        TextField(
            value = positivesValue,
            onValueChange = { positivesValue = it },
            label = { Text("Positives") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
                .height(120.dp)
        )

        TextField(
            value = negativesValue,
            onValueChange = { negativesValue = it },
            label = { Text("Negatives") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
                .height(120.dp)
        )

        TextField(
            value = additionalDetailsValue,
            onValueChange = { additionalDetailsValue = it },
            label = { Text("Additional Details") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
                .height(120.dp)
        )

        Spacer(modifier = Modifier.weight(1f))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(onClick = onCancel) {
                Text("Cancel")
            }
            Button(onClick = {
                val moodEntry = MoodEntry(
                    date = date,
                    moodRating = moodRatingValue,
                    sleepQuality = sleepQualityValue,
                    positives = positivesValue.text,
                    negatives = negativesValue.text,
                    additionalDetails = additionalDetailsValue.text
                )
                onSubmit(moodEntry)
            }) {
                Text("Submit")
            }
        }
    }
}
