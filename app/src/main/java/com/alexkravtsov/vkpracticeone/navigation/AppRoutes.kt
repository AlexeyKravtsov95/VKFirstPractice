package com.alexkravtsov.vkpracticeone.navigation

import kotlinx.serialization.Serializable

@Serializable
data object AppListRoute

@Serializable
data class AppDetailsRoute(val name: String)