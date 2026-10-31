package com.alexkravtsov.vkpracticeone.presentation.detailcard

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.alexkravtsov.vkpracticeone.domain.AppModel

@SuppressLint("LocalContextGetResourceValueCall")
@Composable
fun AppDetailsScreen(
    app: AppModel,
    modifier: Modifier = Modifier,
    onShowcaseAppClick: (AppModel) -> Unit,
    onBackClick: () -> Unit,
) {
    val viewModel = viewModel<AppDetailsViewModel>()
    val state by viewModel.state.collectAsStateWithLifecycle()
    val snackbarHostState = remember { SnackbarHostState() }
    val context = LocalContext.current
    LaunchedEffect(context) {
        viewModel.events.collect { event ->
            when (event) {
                is AppDetailsEvent.ShowSnackbar ->
                    snackbarHostState.showSnackbar(context.getString(event.messageRes))
            }
        }
    }

    Box(modifier.fillMaxSize()) {
        when (val currentState = state) {
            is AppDetailsState.Content -> Content(
                app = app,
                showcase = currentState.showcase,
                onInstallClick = viewModel::onButtonClick,
                onShowcaseAppClick = onShowcaseAppClick,
                onBackClick = onBackClick
            )
            is AppDetailsState.Error -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center,
                ) {
                    Text("Что-то пошло не так!")
                }
            }
            is AppDetailsState.Loading -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center,
                ) {
                    CircularProgressIndicator()
                }
            }
        }
        SnackbarHost(hostState = snackbarHostState, modifier = Modifier.align(Alignment.BottomCenter))
    }
}

@Composable
private fun Content(
    app: AppModel,
    showcase: List<AppModel>,
    onInstallClick: () -> Unit,
    onShowcaseAppClick: (AppModel) -> Unit,
    onBackClick: () -> Unit,
) {
    Column(modifier = Modifier
        .statusBarsPadding().verticalScroll(rememberScrollState())
    ) {
        Toolbar(
            onBackClick = onBackClick,
            onShareClick = {}
        )
        Spacer(Modifier.height(8.dp))
        AppDetailsHeader(
            app = app,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
        Spacer(Modifier.height(8.dp))
        InstallButton(
            onClick = onInstallClick,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        )
        HorizontalDivider()
        ShowcaseBlock(
            showcase,
            onAppClick = onShowcaseAppClick
        )
    }
}