package com.loc.wibuapp.domain.usecases.anime

import com.loc.wibuapp.data.local.AnimeDao
import com.loc.wibuapp.domain.model.Data
import kotlinx.coroutines.flow.Flow

class SelectAnime(
    private val animeDao: AnimeDao
) {

    operator fun invoke(): Flow<List<Data>> {
        return animeDao.getSeasonNow()
    }
}