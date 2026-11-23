package com.alexkravtsov.vkpracticeone.data

import com.alexkravtsov.vkpracticeone.domain.AppModel
import javax.inject.Inject

class AppModelMapper @Inject constructor() {
    fun map(dto: AppModelDto): AppModel = AppModel(
        id = dto.id,
        name = dto.name,
        description = dto.description,
        category = dto.category,
        iconUrl = dto.iconUrl
    )

    fun map(dto: List<AppModelDto>): List<AppModel> = dto.map { map(it) }

    fun map(dto: AppDetailsDto): AppModel = AppModel(
        id = dto.id,
        name = dto.name,
        description = dto.description,
        category = dto.category,
        iconUrl = dto.iconUrl,
    )

    fun toDto(model: AppModel): AppModelDto = AppModelDto(
        id = model.id,
        name = model.name,
        description = model.description,
        category = model.category,
        iconUrl = model.iconUrl
    )

    fun toDto(models: List<AppModel>): List<AppModelDto> = models.map { toDto(it) }
}