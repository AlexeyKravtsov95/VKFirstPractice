package com.alexkravtsov.vkpracticeone.data

class FakeCatalogApi(
    private val catalog: List<AppModelDto> = emptyList(),
    private val error: Exception? = null,
) : CatalogApi {
    override suspend fun getCatalog(): List<AppModelDto> {
        error?.let { throw it }
        return catalog
    }

    override suspend fun getAppById(id: String): AppDetailsDto =
        AppDetailsDto(id = id, name = "name", description = "description", category = "category")
}
