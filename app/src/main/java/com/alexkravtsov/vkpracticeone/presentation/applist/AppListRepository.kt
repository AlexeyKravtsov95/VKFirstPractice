package com.alexkravtsov.vkpracticeone.presentation.applist

import com.alexkravtsov.vkpracticeone.model.AppModel
import com.alexkravtsov.vkpracticeone.model.appsData

class AppListRepository {
    fun get(): List<AppModel> = appsData
}