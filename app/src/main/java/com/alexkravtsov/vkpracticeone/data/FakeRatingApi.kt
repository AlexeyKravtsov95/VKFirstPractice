package com.alexkravtsov.vkpracticeone.data

import javax.inject.Inject
import kotlinx.coroutines.delay

class FakeRatingApi @Inject constructor() {
    suspend fun getRating(id: String): Float {
        delay(1500)
        return (30..50).random() / 10f
    }
}
