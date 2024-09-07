package com.loc.wibuapp.domain.repository

import androidx.paging.PagingData
import com.loc.wibuapp.domain.model.Data
import kotlinx.coroutines.flow.Flow

interface AnimeRepository {

    fun getSeasonNow(data: List<String>): Flow<PagingData<Data>>

    fun getAnimeSearch(searchQuery: String,data: List<String>): Flow<PagingData<Data>>


}