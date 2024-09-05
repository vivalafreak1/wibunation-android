package com.loc.wibuapp.domain.model

data class Trailer(
    val embed_url: String,
    val images: com.loc.wibuapp.domain.model.ImagesX,
    val url: String,
    val youtube_id: String
)