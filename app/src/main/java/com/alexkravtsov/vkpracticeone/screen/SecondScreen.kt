package com.alexkravtsov.vkpracticeone.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun SecondScreen(
    modifier: Modifier,
    text: String?
) {
    Box(modifier = modifier
        .fillMaxSize()
    ) {
        if (text != null) {
            Text(text = text)
        }
    }
}