package com.alexkravtsov.vkpracticeone.screen.detailcard

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.alexkravtsov.vkpracticeone.R
import com.alexkravtsov.vkpracticeone.model.AppModel
import com.alexkravtsov.vkpracticeone.model.showcaseApps

@Composable
fun AppDetailsScreen(
    app: AppModel,
    modifier: Modifier = Modifier,
    onShowcaseAppClick: (AppModel) -> Unit,
    onBackClick: () -> Unit,
) {
    val underDevelopmentText = stringResource(R.string.under_development)
    val context = LocalContext.current

    Column(modifier = modifier
        .verticalScroll(rememberScrollState())
        .statusBarsPadding()
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
            onClick = {
                Toast.makeText(context, underDevelopmentText, Toast.LENGTH_SHORT).show()
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        )
        HorizontalDivider()
        ShowcaseBlock(
            showcaseApps,
            onAppClick = onShowcaseAppClick
        )
    }
}