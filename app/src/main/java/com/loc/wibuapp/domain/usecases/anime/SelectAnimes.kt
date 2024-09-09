package com.loc.wibuapp.domain.usecases.anime

import com.loc.wibuapp.domain.model.Data
import com.loc.wibuapp.domain.repository.AnimeRepository
import kotlinx.coroutines.flow.Flow

class SelectAnimes(
    private val animeRepository: AnimeRepository
) {

    operator fun invoke(): Flow<List<Data>> {
        return animeRepository.selectAnimes()
    }
}