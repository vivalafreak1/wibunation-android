package com.loc.wibuapp.domain.usecases

import com.loc.wibuapp.domain.manager.LocalUserManager
import kotlinx.coroutines.flow.Flow

class ReadAppEntry(
    private val localUserManager: LocalUserManager
) {

    suspend operator fun invoke(): Flow<Boolean>{
        return localUserManager.readAppEntry()
    }
}