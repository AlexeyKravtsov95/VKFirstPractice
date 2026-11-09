package com.alexkravtsov.vkpracticeone.data

import com.alexkravtsov.vkpracticeone.domain.AppModel
import com.alexkravtsov.vkpracticeone.domain.AppModelRepository
import javax.inject.Inject

class AppModelRepositoryImpl @Inject constructor(
    private val api: CatalogApi,
    private val mapper: AppModelMapper,
) : AppModelRepository {
    override suspend fun get(): List<AppModel> = mapper.map(api.getCatalog())

    override suspend fun getShowcase(): List<AppModel> = mapper.map(showcaseApps)
}