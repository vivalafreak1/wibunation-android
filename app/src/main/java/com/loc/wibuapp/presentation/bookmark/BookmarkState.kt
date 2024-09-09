package com.loc.wibuapp.presentation.bookmark

import com.loc.wibuapp.domain.model.Data

data class BookmarkState(
    val data: List<Data> = emptyList()
)
