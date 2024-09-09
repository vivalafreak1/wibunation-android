package com.loc.wibuapp.di

import android.app.Application
import androidx.room.Room
import com.loc.wibuapp.data.local.AnimeDao
import com.loc.wibuapp.data.local.AnimeDatabase
import com.loc.wibuapp.data.local.AnimeTypeConverter
import com.loc.wibuapp.data.manager.LocalUserManagerImpl
import com.loc.wibuapp.data.remote.AnimeApi
import com.loc.wibuapp.data.repository.AnimeRepositoryImpl
import com.loc.wibuapp.domain.manager.LocalUserManager
import com.loc.wibuapp.domain.repository.AnimeRepository
import com.loc.wibuapp.domain.usecases.anime.AnimeUseCase
import com.loc.wibuapp.domain.usecases.anime.DeleteAnime
import com.loc.wibuapp.domain.usecases.anime.GetSeasonNow
import com.loc.wibuapp.domain.usecases.anime.SearchAnime
import com.loc.wibuapp.domain.usecases.anime.SelectAnime
import com.loc.wibuapp.domain.usecases.anime.SelectAnimes
import com.loc.wibuapp.domain.usecases.anime.UpsertAnime
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

import com.loc.wibuapp.util.Constants.ANIME_DATABASE_NAME

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
        animeApi: AnimeApi,
        animeDao: AnimeDao
    ): AnimeRepository = AnimeRepositoryImpl(animeApi, animeDao)

    @Provides
    @Singleton
    fun provideAnimeUseCases(
        animeRepository: AnimeRepository,
        animeDao: AnimeDao
    ): AnimeUseCase {
        return AnimeUseCase(
            getSeasonNow = GetSeasonNow(animeRepository),
            getAnimeSearch = SearchAnime(animeRepository),
            upsertAnime = UpsertAnime(animeRepository),
            deleteAnime = DeleteAnime(animeRepository),
            selectAnimes = SelectAnimes(animeRepository),
            selectAnime = SelectAnime(animeRepository)
        )
    }

    @Provides
    @Singleton
    fun provideAnimeDatabase(
        application: Application
    ): AnimeDatabase {
        return Room.databaseBuilder(
            context = application,
            klass = AnimeDatabase::class.java,
            name = ANIME_DATABASE_NAME
        ).addTypeConverter(AnimeTypeConverter())
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideAnimeDao(
        animeDatabase: AnimeDatabase
    ): AnimeDao = animeDatabase.animeDao
}