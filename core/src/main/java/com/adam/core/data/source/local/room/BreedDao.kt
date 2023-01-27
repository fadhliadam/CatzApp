package com.adam.core.data.source.local.room

import androidx.room.*
import com.adam.core.data.source.local.entity.BreedEntity
import com.adam.core.data.source.local.entity.CatEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface BreedDao {
    @Query("SELECT * FROM breed")
    fun getAllBreed(): Flow<List<BreedEntity>>

    @Query("SELECT * FROM breed where isFavorite = 1")
    fun getFavoriteBreed(): Flow<List<BreedEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBreed(cat: List<BreedEntity>)

    @Query("SELECT * FROM cat where breedsId = :breedsId")
    fun getCatImageBreed(breedsId: String): Flow<List<CatEntity>>

    @Query("Select * FROM cat")
    fun getRandomCatImage(): Flow<List<CatEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCat(cat: List<CatEntity>)

    @Query("UPDATE cat SET breedsId = :breedsId WHERE breedsId = 0")
    fun updateCatBreedId(breedsId: String)

    @Update
    fun updateFavoriteBreed(cat: BreedEntity)
}