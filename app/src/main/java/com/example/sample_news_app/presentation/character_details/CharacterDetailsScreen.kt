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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.sample_news_app.R
import com.example.sample_news_app.presentation.character_details.model.CharacterDetailsState
import com.example.sample_news_app.presentation.characters.model.NewCharacter
import com.example.sample_news_app.ui.theme.SampleSPappTheme

@Composable
internal fun CharacterDetailsScreen(
    viewModel: CharacterDetailsViewModel,
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
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                titleContentColor = MaterialTheme.colorScheme.primary,
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
                    contentDescription = null,
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
                is CharacterDetailsState.Normal -> Normal(screenState.new)
                CharacterDetailsState.Error -> Error()
                CharacterDetailsState.Empty -> Unit
            }
        }
    }
)

@Composable
private fun Normal(new: NewCharacter) = Column(
    modifier = Modifier
        .fillMaxSize()
        .padding(horizontal = 16.dp, vertical = 8.dp)
) {
    Text(
        text = new.name,
        style = MaterialTheme.typography.titleMedium,
    )
    Spacer(modifier = Modifier.height(16.dp))
    Text(
        text = new.sex,
        style = MaterialTheme.typography.bodyLarge,
    )
    Spacer(modifier = Modifier.height(16.dp))
    Text(
        text = new.religion.toString(),
        style = MaterialTheme.typography.bodyMedium,
    )
    Spacer(modifier = Modifier.height(16.dp))
    Text(
        text = new.hairColor.toString(),
        style = MaterialTheme.typography.bodyMedium,
    )
    Spacer(modifier = Modifier.height(16.dp))
    Text(
        text = new.occupation.toString(),
        style = MaterialTheme.typography.bodyMedium,
    )
}

@Composable
private fun Error() = Box(
    modifier = Modifier.fillMaxSize(),
    contentAlignment = Alignment.Center,
) {
    Text(
        text = stringResource(R.string.error),
        style = MaterialTheme.typography.titleLarge,
        color = MaterialTheme.colorScheme.error,
    )
}

@Preview
@Composable
private fun PreviewCharacterDetailsScreenNormal() = SampleSPappTheme {
    ScreenContent(
        screenState = CharacterDetailsState.Normal(
            new = NewCharacter(
                    id = "",
                    name = "Character name",
                    sex = "Character sex",
                    hairColor = "Character hair color",
                    occupation = "Character occupation",
                    religion = "Character religion"
                )
            ), navigationBack = {}
        )
}

@Preview
@Composable
private fun PreviewNewDetailsScreenError() = SampleSPappTheme() {
    ScreenContent(
        screenState = CharacterDetailsState.Error,
        navigationBack = {}
    )
}