package com.adam.core.data.source.local

import com.adam.core.data.source.local.entity.BreedEntity
import com.adam.core.data.source.local.entity.CatEntity
import com.adam.core.data.source.local.room.BreedDao
import kotlinx.coroutines.flow.Flow

class LocalDataSource(private val breedDao: BreedDao) {
    fun getAllCat(): Flow<List<BreedEntity>> = breedDao.getAllBreed()

    fun getFavoriteBreed(): Flow<List<BreedEntity>> = breedDao.getFavoriteBreed()

    fun getCatImageBreed(breedsId: String): Flow<List<CatEntity>> = breedDao.getCatImageBreed(breedsId)

    fun getRandomCatImage(): Flow<List<CatEntity>> = breedDao.getRandomCatImage()

    suspend fun insertBreed(breedList: List<BreedEntity>) = breedDao.insertBreed(breedList)

    suspend fun insertCat(catList: List<CatEntity>) = breedDao.insertCat(catList)

    fun setCatBreedId(cat: List<CatEntity>, breedsId: String){
        cat.filter { it.breedsId == "0" }.forEach{it.breedsId = breedsId}
        breedDao.updateCatBreedId(breedsId)
    }

    fun setFavoriteTourism(breed: BreedEntity, newState: Boolean) {
        breed.isFavorite = newState
        breedDao.updateFavoriteBreed(breed)
    }
}