package com.loc.wibuapp.presentation.detail

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.loc.wibuapp.domain.model.Data
import com.loc.wibuapp.domain.usecases.anime.AnimeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val animeUseCase: AnimeUseCase
) : ViewModel() {

    var sideEffect by mutableStateOf<String?>(null)
        private set

    fun onEvent(event: DetailEvent) {
        when (event) {
            is DetailEvent.UpsertDeleteAnime -> {
                viewModelScope.launch {
                    val data = animeUseCase.selectAnime(event.data.mal_id)
                    if(data == null){
                        upsertAnime(event.data)
                    } else {
                        deleteAnime(event.data)
                    }
                }
            }

            is DetailEvent.RemoveSideEffect -> {
                sideEffect = null
            }
        }
    }

    private suspend fun deleteAnime(data: Data) {
        animeUseCase.deleteAnime(data = data)
        sideEffect = "Anime Deleted"
    }

    private suspend fun upsertAnime(data: Data) {
        animeUseCase.upsertAnime(data = data)
        sideEffect = "Anime Saved"
    }

}