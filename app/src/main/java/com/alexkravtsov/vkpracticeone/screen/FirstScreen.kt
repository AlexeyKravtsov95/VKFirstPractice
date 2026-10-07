package com.alexkravtsov.vkpracticeone.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.alexkravtsov.vkpracticeone.utils.Utils

@Composable
fun FirstScreen(modifier: Modifier) {
    val context = LocalContext.current
    var text by rememberSaveable { mutableStateOf("") }

    val isValidText = text.isNotBlank()

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            value = text,
            onValueChange = {
                text = it
            },
            label = { Text("Введите текст")},
            isError = text.isNotEmpty() && !isValidText
        )
        Button(
            onClick = { Utils.openActivity(context, text) },
            enabled = isValidText
        ) {
            Text("Открыть вторую Activity")
        }

        Button(
            onClick = { Utils.openDialer(context, text) },
            enabled = isValidText,
        ) {
            Text("Позвонить другу")
        }

        Button(
            onClick = { Utils.openShare(context, text) },
            enabled = isValidText,
        ) {
            Text("Поделиться через...")
        }
    }
}