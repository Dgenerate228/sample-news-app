package com.example.sample_news_app.presentation.character_details

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.sample_news_app.R
import com.example.sample_news_app.presentation.character_details.model.CharacterDetailsState
import com.example.sample_news_app.presentation.character_details.model.CharacterDetails
import com.example.sample_news_app.ui.theme.SampleSPappTheme

@Composable
internal fun CharacterDetailsScreen(
    viewModel: CharacterDetailsViewModel = hiltViewModel(),
    navigationBack: () -> Unit,
) {
    val screenState by viewModel.screenState.collectAsStateWithLifecycle()

    ScreenContent(
        screenState = screenState,
        navigationBack = navigationBack
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ScreenContent(
    screenState: CharacterDetailsState,
    navigationBack: () -> Unit,
) = Scaffold(
    modifier = Modifier.fillMaxSize(),
    topBar = {
        TopAppBar(
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = colorResource(R.color.orange),
                titleContentColor = colorResource(R.color.brown)
            ),
            title = {
                Text(
                    text = stringResource(R.string.top_bar_details),
                )
            },
            navigationIcon = {
                Image(
                    modifier = Modifier.clickable { navigationBack() },
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = null
                )
            }
        )
    },
    content = { innerPadding ->
        Box(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            when (screenState) {
                is CharacterDetailsState.Normal -> Normal(character = screenState.character)
                CharacterDetailsState.Error -> Error()
                CharacterDetailsState.Empty -> Unit
            }
        }
    }
)

@Composable
private fun Normal(character: CharacterDetails) = Column(
    modifier = Modifier
        .fillMaxSize()
        .padding(horizontal = 16.dp, vertical = 8.dp)
) {
    Text(
        text = stringResource(R.string.name_ru) + " " + character.name,
        style = MaterialTheme.typography.titleMedium,
    )
    Spacer(modifier = Modifier.height(16.dp))
    Text(
        text = stringResource(R.string.sex_ru) + " " + character.sex,
        style = MaterialTheme.typography.bodyLarge,
    )
    Spacer(modifier = Modifier.height(16.dp))
    Text(
        text = stringResource(R.string.religion_ru) + " " + character.religion.toString(),
        style = MaterialTheme.typography.bodyMedium,
    )
    Spacer(modifier = Modifier.height(16.dp))
    Text(
        text = stringResource(R.string.hair_color_ru) + " " + character.hairColor.toString(),
        style = MaterialTheme.typography.bodyMedium,
    )
    Spacer(modifier = Modifier.height(16.dp))
    Text(
        text = stringResource(R.string.occupation_ru) + " " + character.occupation.toString(),
        style = MaterialTheme.typography.bodyMedium,
    )
}

@Composable
private fun Error() = Box(
    modifier = Modifier.fillMaxSize(),
    contentAlignment = Alignment.Center
) {
    Text(
        text = stringResource(R.string.error),
        style = MaterialTheme.typography.titleLarge,
        color = MaterialTheme.colorScheme.error
    )
}

@Preview
@Composable
private fun PreviewCharacterDetailsScreenNormal() = SampleSPappTheme {
    ScreenContent(
        screenState = CharacterDetailsState.Normal(
            character = CharacterDetails(
                id = stringResource(R.string.empty),
                name = stringResource(R.string.name),
                sex = stringResource(R.string.sex),
                hairColor = stringResource(R.string.hair_color),
                occupation = stringResource(R.string.occupation),
                religion = stringResource(R.string.religion),
            )
        ), navigationBack = {}
    )
}

@Preview
@Composable
private fun PreviewNewDetailsScreenError() = SampleSPappTheme {
    ScreenContent(
        screenState = CharacterDetailsState.Error,
        navigationBack = {}
    )
}