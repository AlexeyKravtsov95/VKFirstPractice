package com.alexkravtsov.vkpracticeone.di

import com.alexkravtsov.vkpracticeone.data.AppModelRepositoryImpl
import com.alexkravtsov.vkpracticeone.domain.AppModelRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {

    @Singleton
    @Binds
    abstract fun bindAppModelRepository(impl: AppModelRepositoryImpl): AppModelRepository
}