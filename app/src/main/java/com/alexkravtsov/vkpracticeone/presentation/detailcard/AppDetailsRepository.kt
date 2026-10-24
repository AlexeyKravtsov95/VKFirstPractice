package com.alexkravtsov.vkpracticeone.presentation.detailcard

import com.alexkravtsov.vkpracticeone.model.AppModel
import com.alexkravtsov.vkpracticeone.model.showcaseApps

class AppDetailsRepository {
    fun get(): List<AppModel> = showcaseApps
}