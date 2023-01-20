package com.adam.core.data.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "breed")
data class BreedEntity(
    @PrimaryKey
    @ColumnInfo(name = "breedId")
    var breedId: String,

    @ColumnInfo(name = "breedName")
    var breedName: String,

    @ColumnInfo(name = "description")
    var description: String,

    @ColumnInfo(name = "origin")
    var origin: String,

    @ColumnInfo(name = "countryCode")
    var countryCode: String,

    @ColumnInfo(name = "sheddingLevel")
    var sheddingLevel: Int,

    @ColumnInfo(name = "dogFriendly")
    var dogFriendly: Int,

    @ColumnInfo(name = "healthIssues")
    var healthIssues: Int,

    @ColumnInfo(name = "adaptability")
    var adaptability: Int,

    @ColumnInfo(name = "intelligence")
    var intelligence: Int,

    @ColumnInfo(name = "socialNeeds")
    var socialNeeds: Int,

    @ColumnInfo(name = "childFriendly")
    var childFriendly: Int,

    @ColumnInfo(name = "temperament")
    var temperament: String,

    @ColumnInfo(name = "grooming")
    var grooming: Int,

    @ColumnInfo(name = "energyLevel")
    var energyLevel: Int,

    @ColumnInfo(name = "strangerFriendly")
    var strangerFriendly: Int,

    @ColumnInfo(name = "affectionLevel")
    var affectionLevel: Int,

    @ColumnInfo(name = "isFavorite")
    var isFavorite: Boolean = false
)