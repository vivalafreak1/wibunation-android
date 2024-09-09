package com.loc.wibuapp.domain.usecases.anime

import com.loc.wibuapp.domain.model.Data
import com.loc.wibuapp.domain.repository.AnimeRepository

class SelectAnime(
    private val animeRepository: AnimeRepository
) {

    suspend operator fun invoke(mal_id: Int): Data? {
        return animeRepository.selectAnime(mal_id)
    }
}