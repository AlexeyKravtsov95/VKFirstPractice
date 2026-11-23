package com.alexkravtsov.vkpracticeone.domain

import java.io.IOException
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class GetAppsUseCaseTest {

    private val apps = listOf(
        AppModel(
            id = "1",
            name = "First",
            description = "First app",
            category = "Tools",
            iconUrl = null
        )
    )

    @Test
    fun `returns apps from repository`() = runTest {
        val useCase = GetAppsUseCase(FakeAppModelRepository(apps = apps))

        assertEquals(apps, useCase())
    }

    @Test
    fun `requests apps from repository once`() = runTest {
        val repository = FakeAppModelRepository(apps = apps)
        val useCase = GetAppsUseCase(repository)

        useCase()

        assertEquals(1, repository.getCallsCount)
    }

    @Test
    fun `propagates repository error`() = runTest {
        val useCase = GetAppsUseCase(FakeAppModelRepository(error = IOException()))

        val error = runCatching { useCase() }.exceptionOrNull()

        assertTrue(error is IOException)
    }
}
