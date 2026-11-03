package com.alexkravtsov.vkpracticeone.data

import com.alexkravtsov.vkpracticeone.domain.AppModel
import com.alexkravtsov.vkpracticeone.domain.AppModelRepository
import javax.inject.Inject

class AppModelRepositoryImpl @Inject constructor(
    private val api: AppModelApi,
    private val mapper: AppModelMapper,
) : AppModelRepository {
    override suspend fun get(): List<AppModel> {
        val dto = api.getApps()
        val domain = mapper.map(dto)

        return domain
    }

    override suspend fun getShowcase(): List<AppModel> {
        val dto = api.getShowcase()
        val domain = mapper.map(dto)

        return domain
    }
}