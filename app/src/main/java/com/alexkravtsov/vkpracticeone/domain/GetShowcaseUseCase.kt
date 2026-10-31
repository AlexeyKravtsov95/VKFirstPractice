package com.alexkravtsov.vkpracticeone.domain

class GetShowcaseUseCase(
    private val repository: AppModelRepository
) {
    suspend operator fun invoke(): List<AppModel> {
        return repository.getShowcase()
    }
}
