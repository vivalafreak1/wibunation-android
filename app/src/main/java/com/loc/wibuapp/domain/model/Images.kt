package com.loc.wibuapp.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Images(
    val jpg: Jpg?,
    val webp: Webp
): Parcelable