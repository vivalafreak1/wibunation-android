package com.loc.wibuapp.domain.usecases.anime

import com.loc.wibuapp.domain.model.Data
import com.loc.wibuapp.domain.repository.AnimeRepository

class DeleteAnime(
    private val animeRepository: AnimeRepository
) {

    suspend operator fun invoke(data: Data) {
        animeRepository.deleteAnime(data)
    }
}