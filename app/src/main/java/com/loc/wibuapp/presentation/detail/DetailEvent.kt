package com.loc.wibuapp.presentation.detail

import com.loc.wibuapp.domain.model.Data

sealed class DetailEvent {

    data class UpsertDeleteAnime(val data: Data): DetailEvent()

    object RemoveSideEffect: DetailEvent()
}