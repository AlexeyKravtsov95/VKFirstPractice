package com.alexkravtsov.vkpracticeone.data

import com.alexkravtsov.vkpracticeone.domain.AppModel
import javax.inject.Inject

class AppModelMapper @Inject constructor() {
    fun map(dto: AppModelDto): AppModel = AppModel(
        name = dto.name,
        description = dto.description,
        category = dto.category,
        iconUrl = dto.iconUrl
    )

    fun map(dto: List<AppModelDto>): List<AppModel> = dto.map { map(it) }
}