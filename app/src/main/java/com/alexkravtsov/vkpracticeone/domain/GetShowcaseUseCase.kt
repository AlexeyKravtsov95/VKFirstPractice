package com.alexkravtsov.vkpracticeone.domain

import javax.inject.Inject

class GetShowcaseUseCase @Inject constructor(
    private val repository: AppModelRepository
) {
    suspend operator fun invoke(): List<AppModel> {
        return repository.getShowcase()
    }
}
