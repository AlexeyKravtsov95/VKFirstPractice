package com.alexkravtsov.vkpracticeone.screen.applist

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.alexkravtsov.vkpracticeone.model.AppModel
import com.alexkravtsov.vkpracticeone.ui.theme.VKPracticeOneTheme

@Composable
fun AppListItem(
    app: AppModel,
    modifier: Modifier = Modifier,
    onAppClick: (AppModel) -> Unit = {},
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onAppClick(app)}
            .padding(
                horizontal = 16.dp,
                vertical = 12.dp
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            model = app.iconUrl,
            contentDescription = app.name,
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .size(48.dp)
                .clip(RoundedCornerShape(12.dp)),
        )
        Spacer(Modifier.width(16.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = app.name,
                style = MaterialTheme.typography.titleLarge,
                maxLines = 1
            )
            Spacer(Modifier.height(2.dp))
            Text(
                text = app.description,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(Modifier.height(4.dp))
            Text(
                text = app.category,
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}

@Preview
@Composable
private fun AppListItemPreview() {
    VKPracticeOneTheme {
        AppListItem(
            app = AppModel(
                name = "WhatsApp Messenger",
                description = "WhatsApp from Meta WhatsApp — это БЕСПЛАТНОЕ приложение для видеозвонков и обмена сообщениями",
                category = "Связь",
                iconUrl = "https://thumb.wikimedia.org/wikipedia/commons/thumb/6/6b/WhatsApp.svg/3840px-WhatsApp.svg.png?utm_source=commons.wikimedia.org&utm_campaign=index&utm_content=thumbnail"
            )
        )
    }
}