package com.alexkravtsov.vkpracticeone.screen.applist

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.alexkravtsov.vkpracticeone.R
import com.alexkravtsov.vkpracticeone.model.AppModel
import com.alexkravtsov.vkpracticeone.model.appsData
import com.alexkravtsov.vkpracticeone.ui.theme.VKPracticeOneTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppListScreen(
    apps: List<AppModel>,
    onAppClick: (AppModel) -> Unit,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.title_topbar)) },
                navigationIcon = {
                    Image(
                        painter = painterResource(R.drawable.ic_launcher_foreground),
                        contentDescription = "Иконка приложения",
                        modifier = Modifier
                            .padding(start = 8.dp)
                            .size(32.dp)
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary,
                    navigationIconContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentPadding = PaddingValues(vertical = 8.dp)
        ) {
            items(apps, key = { it.name }) { app ->
                AppListItem(app, onAppClick = onAppClick)
            }
        }
    }
}

@Preview
@Composable
private fun AppListScreenPreview() {
    VKPracticeOneTheme {
        AppListScreen(appsData, onAppClick = {})
    }
}