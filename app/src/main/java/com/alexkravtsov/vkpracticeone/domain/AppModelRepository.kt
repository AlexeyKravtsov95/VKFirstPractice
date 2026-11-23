package com.alexkravtsov.vkpracticeone.domain

interface AppModelRepository {
    suspend fun get(): List<AppModel>
    suspend fun getShowcase(): List<AppModel>
    suspend fun getRating(id: String): Float
    suspend fun getRatingsSequentially(ids: List<String>): Map<String, Float>
    suspend fun getRatingsInParallel(ids: List<String>): Map<String, Float>
}
