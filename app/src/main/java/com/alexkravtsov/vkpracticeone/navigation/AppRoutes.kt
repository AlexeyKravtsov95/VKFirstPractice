package com.alexkravtsov.vkpracticeone.navigation

import com.alexkravtsov.vkpracticeone.domain.AppModel
import kotlinx.serialization.Serializable

@Serializable
data object AppListRoute

@Serializable
data class AppDetailsRoute(val app: AppModel)