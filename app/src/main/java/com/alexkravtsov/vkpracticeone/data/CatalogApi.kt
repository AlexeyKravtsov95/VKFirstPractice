package com.alexkravtsov.vkpracticeone.data

import retrofit2.http.GET
import retrofit2.http.Path

interface CatalogApi {
    @GET("catalog")
    suspend fun getCatalog(): List<AppModelDto>

    @GET("{id}")
    suspend fun getAppById(@Path("id") id: String): AppDetailsDto
}