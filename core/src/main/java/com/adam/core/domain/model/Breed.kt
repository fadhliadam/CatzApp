package com.adam.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Breed(
    val breedId: String,
    val breedName: String,
    val origin: String,
    val countryCode: String,
    val description: String,
    val sheddingLevel: Int,
    val dogFriendly: Int,
    val healthIssues: Int,
    val adaptability: Int,
    val intelligence: Int,
    val socialNeeds: Int,
    val childFriendly: Int,
    val temperament: String,
    val grooming: Int,
    val energyLevel: Int,
    val strangerFriendly: Int,
    val affectionLevel: Int,
    val isFavorite: Boolean
):Parcelable