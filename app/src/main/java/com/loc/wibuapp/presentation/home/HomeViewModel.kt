package com.loc.wibuapp.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.loc.wibuapp.domain.usecases.anime.AnimeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val animeUseCase: AnimeUseCase
): ViewModel() {

    val anime = animeUseCase.getSeasonNow(
        data = listOf("mal_id", "title", "images/jpg/image_url", "synopsis")
    ).cachedIn(viewModelScope)
}