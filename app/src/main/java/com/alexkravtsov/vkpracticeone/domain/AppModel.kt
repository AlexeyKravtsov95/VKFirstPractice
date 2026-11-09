package com.alexkravtsov.vkpracticeone.domain

import kotlinx.serialization.Serializable

@Serializable
data class AppModel(
    val id: String,
    val name: String,
    val description: String,
    val category: String,
    val iconUrl: String?
)