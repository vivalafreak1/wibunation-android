package com.loc.wibuapp.di

import android.app.Application
import com.loc.wibuapp.data.manager.LocalUserManagerImpl
import com.loc.wibuapp.domain.manager.LocalUserManager
import com.loc.wibuapp.domain.usecases.AppEntryUseCases
import com.loc.wibuapp.domain.usecases.ReadAppEntry
import com.loc.wibuapp.domain.usecases.SaveAppEntry
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
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
}