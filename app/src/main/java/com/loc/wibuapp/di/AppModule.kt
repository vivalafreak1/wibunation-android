package com.loc.wibuapp.di

import android.app.Application
import com.loc.wibuapp.data.manager.LocalUserManagerImpl
import com.loc.wibuapp.data.remote.AnimeApi
import com.loc.wibuapp.data.repository.AnimeRepositoryImpl
import com.loc.wibuapp.domain.manager.LocalUserManager
import com.loc.wibuapp.domain.repository.AnimeRepository
import com.loc.wibuapp.domain.usecases.anime.AnimeUseCase
import com.loc.wibuapp.domain.usecases.anime.GetSeasonNow
import com.loc.wibuapp.domain.usecases.app_entry.AppEntryUseCases
import com.loc.wibuapp.domain.usecases.app_entry.ReadAppEntry
import com.loc.wibuapp.domain.usecases.app_entry.SaveAppEntry
import com.loc.wibuapp.util.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideLocalUserManager(
        application: Application
    ): LocalUserManager = LocalUserManagerImpl(application)

    @Provides
    @Singleton
    fun provideAppEntryUseCases(
        localUserManager: LocalUserManager
    ) = AppEntryUseCases(
        readAppEntry = ReadAppEntry(localUserManager),
        saveAppEntry = SaveAppEntry(localUserManager),
    )

    @Provides
    @Singleton
    fun provideAnimeApi(): AnimeApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(AnimeApi::class.java)
    }

    @Provides
    @Singleton
    fun provideAnimeRepository(
        animeApi: AnimeApi
    ): AnimeRepository = AnimeRepositoryImpl(animeApi)

    @Provides
    @Singleton
    fun provideAnimeUseCases(
        animeRepository: AnimeRepository
    ): AnimeUseCase {
        return AnimeUseCase(
            getSeasonNow = GetSeasonNow(animeRepository)
        )
    }

}