package com.alexkravtsov.vkpracticeone.domain

interface AppModelRepository {
    suspend fun get(): List<AppModel>
    suspend fun getShowcase(): List<AppModel>
}