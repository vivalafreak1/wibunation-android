package com.loc.wibuapp.data.remote.dto

import com.loc.wibuapp.domain.model.Data
import com.loc.wibuapp.domain.model.Pagination

data class AnimeResponse(
    val data: List<Data>,
    val pagination: Pagination
)