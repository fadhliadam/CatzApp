package com.adam.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Cat(
    val width: Int,
    val id: String,
    val url: String,
    val height: Int,
    val breedsId: String,
): Parcelable