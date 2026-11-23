package com.alexkravtsov.vkpracticeone.data

import com.alexkravtsov.vkpracticeone.domain.AppModel
import com.alexkravtsov.vkpracticeone.domain.AppModelRepository
import javax.inject.Inject
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope

class AppModelRepositoryImpl @Inject constructor(
    private val api: CatalogApi,
    private val mapper: AppModelMapper,
    private val ratingApi: FakeRatingApi,
) : AppModelRepository {
    override suspend fun get(): List<AppModel> = mapper.map(api.getCatalog())

    override suspend fun getShowcase(): List<AppModel> = mapper.map(showcaseApps)

    override suspend fun getRating(id: String): Float = ratingApi.getRating(id)

    override suspend fun getRatingsSequentially(ids: List<String>): Map<String, Float> =
        ids.associate { id -> id to getRating(id) }

    override suspend fun getRatingsInParallel(ids: List<String>): Map<String, Float> =
        coroutineScope {
            ids.map { id -> async { id to getRating(id) } }.awaitAll().toMap()
        }
}
