package com.loc.wibuapp.domain.usecases.anime

import com.loc.wibuapp.domain.model.Data
import com.loc.wibuapp.domain.repository.AnimeRepository

class UpsertAnime(
    private val animeRepository: AnimeRepository
) {

    suspend operator fun invoke(data: Data) {
        animeRepository.upsertAnime(data)
    }
}