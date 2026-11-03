package com.alexkravtsov.vkpracticeone.data

import kotlinx.coroutines.delay
import javax.inject.Inject
import kotlin.time.Duration.Companion.milliseconds

class AppModelApi @Inject constructor() {
    suspend fun getApps(): List<AppModelDto> {
        delay(1500L.milliseconds)
        return appsData
    }

    fun getShowcase(): List<AppModelDto> = showcaseApps
}