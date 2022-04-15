package com.kunal.actmobile.di

import com.kunal.actmobile.data.network.apis.CountryApi
import com.kunal.actmobile.data.network.apis.ImageApi
import com.kunal.actmobile.data.repositories.CountryRepository
import com.kunal.actmobile.data.repositories.ImageRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideImageRepository(
        api: ImageApi
    ): ImageRepository {
        return ImageRepository(api)
    }

    @Singleton
    @Provides
    fun provideCountryRepository(
        api: CountryApi
    ): CountryRepository {
        return CountryRepository(api)
    }

}