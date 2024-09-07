package com.loc.wibuapp.presentation.search

import androidx.paging.PagingData
import com.loc.wibuapp.domain.model.Data
import kotlinx.coroutines.flow.Flow

data class SearchState(
    val searchQuery: String = "",
    val data: Flow<PagingData<Data>>? = null
)