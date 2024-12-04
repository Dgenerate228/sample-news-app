package com.example.sample_news_app.presentation

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.sample_news_app.R
import com.example.sample_news_app.presentation.model.MainState
import com.example.sample_news_app.presentation.model.New as NewList
import com.example.sample_news_app.ui.theme.SamplenewsappTheme

@Composable
internal fun MainScreen(viewModel: MainViewModel = viewModel()) {
    val screenState by viewModel.screenState.collectAsStateWithLifecycle()
    ScreenContent(screenState = screenState)

}

@SuppressLint("ResourceAsColor")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ScreenContent(screenState: MainState) {
    Scaffold(
        modifier = Modifier.fillMaxSize().background(colorResource(R.color.brown)),

        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = colorResource(R.color.orange),
                    titleContentColor = colorResource(R.color.brown)
                ),
                title = {
                    Text(
                        text = "Select character"
                    )
                }
            )
        },
        content = { innerPadding ->
            Box(modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()) {
                when (screenState) {
                    is MainState.Normal -> Normal(screenState.characters)
                    MainState.Loading -> Loading()
                    MainState.Error -> Error()
                }
            }
        }
    )
}

@Composable
private fun Normal(spApi: List<NewList>) = Column(
    modifier = Modifier
        .fillMaxSize()
        .verticalScroll(rememberScrollState())
) {
    spApi.forEach {
        New(
            name = it.name,
            sex = it.sex

        )
    }
}

@Composable
private fun Loading() = Box(
    modifier = Modifier.fillMaxSize(),
    contentAlignment = Alignment.Center,
) {
    CircularProgressIndicator()
}

@Composable
private fun Error() = Box(
    modifier = Modifier.fillMaxSize(),
    contentAlignment = Alignment.Center,
) {
    Text(
        text = "Unknown Error",
        style = MaterialTheme.typography.titleLarge,
        color = MaterialTheme.colorScheme.error,
    )
}

@Composable
private fun New(name: String,
                sex: String,
) = Card(
    modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 16.dp, vertical = 8.dp),
    content = {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
        ) {
            Text(
                text = name,
                style = MaterialTheme.typography.titleMedium,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = sex,
                style = MaterialTheme.typography.bodyMedium,
                color = colorResource(R.color.dark_grey)
            )
        }
    }
)

@Preview
@Composable
private fun PreviewMainScreenNormal() = SamplenewsappTheme {
    ScreenContent(
        screenState = MainState.Normal(
            characters = listOf(
                NewList(
                    name = "Character name",
                    sex = "Character sex"
                ),
            )
        )
    )
}

@Preview
@Composable
private fun PreviewMainScreenLoading() = SamplenewsappTheme() {
    ScreenContent(screenState = MainState.Loading)
}

@Preview
@Composable
private fun PreviewMainScreenError() = SamplenewsappTheme {
    ScreenContent(screenState = MainState.Error)
}