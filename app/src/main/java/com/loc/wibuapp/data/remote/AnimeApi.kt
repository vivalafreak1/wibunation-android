package com.loc.wibuapp.data.remote

import com.loc.wibuapp.data.remote.dto.AnimeResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface AnimeApi {

    @GET("seasons/now")
    suspend fun getSeasonNow(
        @Query("page") page: Int,
        @Query("limit") limit: Int? = 10,         // Optional limit, default to 10 items per page
        @Query("filter") filter: String? = null,  // Optional filter (tv, movie, etc.)
        @Query("sfw") sfw: Boolean? = null,       // Optional SFW flag
        @Query("continuing") continuing: Boolean? = null // Optional flag for continuing series
        // @Query("data") data: String,
        // @Query("filter") filter: String,
        // @Query("apiKey") apiKey: String
    ): AnimeResponse
}