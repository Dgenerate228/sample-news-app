package com.example.sample_news_app.data.presentation.character_details

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.sample_news_app.ui.theme.SampleSPappTheme

@Composable
internal fun CharacterDetailsScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Character Detail Screen",
            style = MaterialTheme.typography.titleLarge
        )
    }
}

@Preview
@Composable
private fun PreviewCharacterDetailsScreen() = SampleSPappTheme {
    CharacterDetailsScreen()
}