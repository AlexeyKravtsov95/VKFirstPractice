package com.alexkravtsov.vkpracticeone.domain

class FakeAppModelRepository(
    private val apps: List<AppModel> = emptyList(),
    private val showcase: List<AppModel> = emptyList(),
    private val error: Exception? = null,
) : AppModelRepository {
    var getCallsCount = 0
        private set
    var getShowcaseCallsCount = 0
        private set

    override suspend fun get(): List<AppModel> {
        getCallsCount++
        error?.let { throw it }
        return apps
    }

    override suspend fun getShowcase(): List<AppModel> {
        getShowcaseCallsCount++
        error?.let { throw it }
        return showcase
    }

    override suspend fun getRating(id: String): Float = 0f

    override suspend fun getRatingsSequentially(ids: List<String>): Map<String, Float> = emptyMap()

    override suspend fun getRatingsInParallel(ids: List<String>): Map<String, Float> = emptyMap()
}
