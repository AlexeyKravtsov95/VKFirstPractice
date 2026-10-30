package com.alexkravtsov.vkpracticeone.domain

class GetAppsUseCase(
    private val repository: AppModelRepository
) {
    suspend operator fun invoke(): List<AppModel> {
        return repository.get()
    }
}