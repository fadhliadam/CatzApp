package com.adam.core.data.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cat")
data class CatEntity(
    @ColumnInfo(name = "width")
    var width: Int,

    @PrimaryKey
    @ColumnInfo(name = "catId")
    var catId: String,

    @ColumnInfo(name = "url")
    var url: String,

    @ColumnInfo(name = "height")
    var height: Int,

    @ColumnInfo(name = "breedsId")
    var breedsId: String = "0"

)
