package com.alexkravtsov.vkpracticeone.screen.detailcard

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.alexkravtsov.vkpracticeone.model.AppModel
import com.alexkravtsov.vkpracticeone.model.appsData
import com.alexkravtsov.vkpracticeone.ui.theme.VKPracticeOneTheme

@Composable
fun AppDetailsHeader(
    app: AppModel,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
    ) {
        AsyncImage(
            model = app.iconUrl,
            contentDescription = app.name,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(128.dp)
                .clip(RoundedCornerShape(16.dp))
        )
        Spacer(Modifier.width(16.dp))
        Column {
            Text(
                text = app.category,
                color = MaterialTheme.colorScheme.secondary,
                fontSize = 12.sp
            )
            Spacer(Modifier.height(4.dp))
            Text(
                text = app.name,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.headlineSmall
            )
            Text(
                text = app.description,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

@Composable
@Preview
private fun AppDetailsHeader() {
    val app = AppModel(
        name = "Telegram",
        description = "Telegram – простое, быстрое и безопасное приложение для обмена сообщениями.",
        category = "Связь",
        iconUrl = "https://logo-teka.com/wp-content/uploads/2025/06/telegram-logo.png"
    )
    VKPracticeOneTheme {
        AppDetailsHeader(
            app = app,
            modifier = Modifier.fillMaxWidth()
        )
    }
}