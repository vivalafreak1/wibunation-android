package com.loc.wibuapp.domain.usecases.anime

import androidx.paging.PagingData
import com.loc.wibuapp.domain.model.Data
import com.loc.wibuapp.domain.repository.AnimeRepository
import kotlinx.coroutines.flow.Flow

class GetSeasonNow(
    private val animeRepository: AnimeRepository
) {

    operator fun invoke(data: List<String>): Flow<PagingData<Data>>{
        return animeRepository.getSeasonNow(data = data)
    }
}