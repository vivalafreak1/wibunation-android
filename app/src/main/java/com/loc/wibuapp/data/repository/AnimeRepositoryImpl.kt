package com.loc.wibuapp.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.loc.wibuapp.data.remote.AnimeApi
import com.loc.wibuapp.data.remote.AnimePagingSource
import com.loc.wibuapp.data.remote.SearchAnimePagingSource
import com.loc.wibuapp.domain.model.Data
import com.loc.wibuapp.domain.repository.AnimeRepository
import kotlinx.coroutines.flow.Flow

class AnimeRepositoryImpl(
    private val animeApi: AnimeApi
) : AnimeRepository {

    override fun getSeasonNow(data: List<String>): Flow<PagingData<Data>> {
        return Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = {
                AnimePagingSource(
                    animeApi = animeApi,
                    data = data.joinToString(separator = ",")
                )
            }
        ).flow
    }

    override fun getAnimeSearch(searchQuery: String, data: List<String>): Flow<PagingData<Data>> {
        return Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = {
                SearchAnimePagingSource(
                    searchQuery = searchQuery,
                    animeApi = animeApi,
                    data = data.joinToString(separator = ",")
                )
            }
        ).flow
    }
}