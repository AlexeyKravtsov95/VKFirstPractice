package com.alexkravtsov.vkpracticeone.domain

import java.io.IOException
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class GetShowcaseUseCaseTest {

    private val showcase = listOf(
        AppModel(
            id = "1",
            name = "First",
            description = "First app",
            category = "Tools",
            iconUrl = null
        )
    )

    @Test
    fun `returns showcase from repository`() = runTest {
        val useCase = GetShowcaseUseCase(FakeAppModelRepository(showcase = showcase))

        assertEquals(showcase, useCase())
    }

    @Test
    fun `requests showcase from repository once`() = runTest {
        val repository = FakeAppModelRepository(showcase = showcase)
        val useCase = GetShowcaseUseCase(repository)

        useCase()

        assertEquals(1, repository.getShowcaseCallsCount)
    }

    @Test
    fun `propagates repository error`() = runTest {
        val useCase = GetShowcaseUseCase(FakeAppModelRepository(error = IOException()))

        val error = runCatching { useCase() }.exceptionOrNull()

        assertTrue(error is IOException)
    }
}
