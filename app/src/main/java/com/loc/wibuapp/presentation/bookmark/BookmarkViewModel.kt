package com.loc.wibuapp.presentation.bookmark

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.loc.wibuapp.domain.usecases.anime.AnimeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class BookmarkViewModel @Inject constructor(
    private val animeUseCase: AnimeUseCase
): ViewModel() {

    private val _state = mutableStateOf(BookmarkState())
    val state: State<BookmarkState> = _state

    init {
        getSeasonNow()
    }

    private fun  getSeasonNow(){
        animeUseCase.selectAnime().onEach {
            _state.value = _state.value.copy(data = it)
        }.launchIn(viewModelScope)
    }

}