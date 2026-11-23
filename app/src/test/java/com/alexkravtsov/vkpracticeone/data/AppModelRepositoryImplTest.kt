package com.alexkravtsov.vkpracticeone.data

import java.io.IOException
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class AppModelRepositoryImplTest {

    private val mapper = AppModelMapper()
    private val firstDto = AppModelDto(
        id = "1",
        name = "First",
        description = "First app",
        category = "Tools",
        iconUrl = "https://example.com/1.png"
    )
    private val secondDto = AppModelDto(
        id = "2",
        name = "Second",
        description = "Second app",
        category = "Games",
        iconUrl = null
    )

    private fun repository(api: CatalogApi = FakeCatalogApi()) = AppModelRepositoryImpl(
        api = api,
        mapper = mapper,
        ratingApi = FakeRatingApi(),
    )

    @Test
    fun `get returns domain models mapped from catalog`() = runTest {
        val result = repository(FakeCatalogApi(catalog = listOf(firstDto, secondDto))).get()

        assertEquals(mapper.map(listOf(firstDto, secondDto)), result)
    }

    @Test
    fun `get propagates api error`() = runTest {
        val repository = repository(FakeCatalogApi(error = IOException()))

        val error = runCatching { repository.get() }.exceptionOrNull()

        assertTrue(error is IOException)
    }

    @Test
    fun `getShowcase returns mapped hardcoded showcase`() = runTest {
        val result = repository().getShowcase()

        assertEquals(showcaseApps.map { it.name }, result.map { it.name })
    }

    @Test
    fun `getRating returns value from 3 to 5`() = runTest {
        val rating = repository().getRating("1")

        assertTrue(rating in 3f..5f)
    }

    @Test
    fun `getRatingsSequentially requests ratings one after another`() = runTest {
        val result = repository().getRatingsSequentially(listOf("1", "2", "3"))

        assertEquals(setOf("1", "2", "3"), result.keys)
        assertEquals(4500L, testScheduler.currentTime)
    }

    @Test
    fun `getRatingsInParallel requests ratings at the same time`() = runTest {
        val result = repository().getRatingsInParallel(listOf("1", "2", "3"))

        assertEquals(setOf("1", "2", "3"), result.keys)
        assertEquals(1500L, testScheduler.currentTime)
    }
}
