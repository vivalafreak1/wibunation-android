package com.loc.wibuapp.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.loc.wibuapp.data.local.AnimeDao
import com.loc.wibuapp.data.remote.AnimeApi
import com.loc.wibuapp.data.remote.AnimePagingSource
import com.loc.wibuapp.data.remote.SearchAnimePagingSource
import com.loc.wibuapp.domain.model.Data
import com.loc.wibuapp.domain.repository.AnimeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onEach

class AnimeRepositoryImpl(
    private val animeApi: AnimeApi,
    private val animeDao: AnimeDao
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

    override suspend fun upsertAnime(data: Data) {
        animeDao.upsert(data)
    }

    override suspend fun deleteAnime(data: Data) {
        animeDao.delete(data)
    }

    override fun selectAnimes(): Flow<List<Data>> {
        return animeDao.getSeasonNow()
    }

    override suspend fun selectAnime(mal_id: Int): Data? {
        return animeDao.getAnimeById(mal_id)
    }
}