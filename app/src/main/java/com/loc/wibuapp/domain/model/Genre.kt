package com.loc.wibuapp.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Genre(
    val mal_id: Int,
    val name: String,
    val type: String,
    val url: String
): Parcelable