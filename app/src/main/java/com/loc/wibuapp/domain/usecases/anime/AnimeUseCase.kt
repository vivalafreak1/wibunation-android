package com.loc.wibuapp.domain.usecases.anime

data class AnimeUseCase(
    val getSeasonNow: GetSeasonNow,
    val getAnimeSearch: SearchAnime,
    val upsertAnime: UpsertAnime,
    val deleteAnime: DeleteAnime,
    val selectAnime: SelectAnime
)
