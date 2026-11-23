package com.alexkravtsov.vkpracticeone.data

import com.alexkravtsov.vkpracticeone.domain.AppModel
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test

class AppModelMapperTest {

    private val mapper = AppModelMapper()

    private val dto = AppModelDto(
        id = "1",
        name = "First",
        description = "First app",
        category = "Tools",
        iconUrl = "https://example.com/1.png"
    )

    private val model = AppModel(
        id = "1",
        name = "First",
        description = "First app",
        category = "Tools",
        iconUrl = "https://example.com/1.png"
    )

    @Test
    fun `maps dto to domain`() {
        assertEquals(model, mapper.map(dto))
    }

    @Test
    fun `keeps null iconUrl when maps dto to domain`() {
        assertNull(mapper.map(dto.copy(iconUrl = null)).iconUrl)
    }

    @Test
    fun `maps dto list to domain list preserving order`() {
        val result = mapper.map(listOf(dto, dto.copy(id = "2")))

        assertEquals(listOf("1", "2"), result.map { it.id })
    }

    @Test
    fun `maps details dto to domain`() {
        val detailsDto = AppDetailsDto(
            id = "1",
            name = "First",
            description = "First app",
            category = "Tools",
            iconUrl = null
        )

        assertEquals(model.copy(iconUrl = null), mapper.map(detailsDto))
    }

    @Test
    fun `maps domain to dto`() {
        assertEquals(dto, mapper.toDto(model))
    }

    @Test
    fun `maps domain list to dto list preserving order`() {
        val result = mapper.toDto(listOf(model, model.copy(id = "2")))

        assertEquals(listOf(dto, dto.copy(id = "2")), result)
    }

    @Test
    fun `dto stays the same after round trip through domain`() {
        assertEquals(dto, mapper.toDto(mapper.map(dto)))
    }
}
