package com.alexkravtsov.vkpracticeone.presentation.detailcard

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.alexkravtsov.vkpracticeone.ui.theme.VKPracticeOneTheme

@Composable
internal fun Toolbar(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    onShareClick: () -> Unit,
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier
            .fillMaxWidth()
    ) {
        IconButton(onClick = onBackClick) {
            Icon(
                imageVector = Icons.AutoMirrored.Default.ArrowBack,
                contentDescription = "Назад",
                tint = MaterialTheme.colorScheme.primary
            )
        }
        IconButton(onClick = onShareClick) {
            Icon(
                imageVector = Icons.Default.Share,
                contentDescription = "Поделиться",
                tint = MaterialTheme.colorScheme.primary
            )
        }
    }
}

@Composable
@Preview
private fun ToolbarPreview() {
    VKPracticeOneTheme {
        Toolbar(
            onBackClick = {},
            onShareClick = {}
        )
    }
}