package com.adam.core.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.adam.core.data.source.local.entity.BreedEntity
import com.adam.core.data.source.local.entity.CatEntity

@Database(entities = [BreedEntity::class, CatEntity::class], version = 2, exportSchema = false)
abstract class CatDatabase: RoomDatabase() {
    abstract fun breedDao(): BreedDao
}