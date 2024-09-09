package com.loc.wibuapp.domain.usecases.anime

import com.loc.wibuapp.data.local.AnimeDao
import com.loc.wibuapp.domain.model.Data

class UpsertAnime(
    private val animeDao: AnimeDao
) {

    suspend operator fun invoke(data: Data) {
        animeDao.upsert(data)
    }
}