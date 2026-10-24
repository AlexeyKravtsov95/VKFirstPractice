package com.alexkravtsov.vkpracticeone.presentation.detailcard

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.alexkravtsov.vkpracticeone.R
import com.alexkravtsov.vkpracticeone.model.AppModel
import com.alexkravtsov.vkpracticeone.presentation.applist.AppListItem

@Composable
fun ShowcaseBlock(
    apps: List<AppModel>,
    onAppClick: (AppModel) -> Unit,
    modifier: Modifier = Modifier
) {
    val pageSize = 3
    val pagerState = rememberPagerState(pageCount = { apps.size / pageSize })

    Column(modifier = modifier.fillMaxWidth()) {
        Text(
            text = stringResource(R.string.title_showcase),
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier
                .padding(
                    start = 16.dp,
                    top = 16.dp,
                    bottom = 8.dp
                )
        )
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.fillMaxWidth()
        ) { page ->
            Column(Modifier.fillMaxWidth()) {
                apps.drop(page * pageSize).take(pageSize).forEach { app ->
                    AppListItem(app, onAppClick = onAppClick)
                }
            }
        }
    }
}