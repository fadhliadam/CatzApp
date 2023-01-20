package com.adam.core.domain.usecase

import com.adam.core.data.Resource
import com.adam.core.domain.model.Breed
import com.adam.core.domain.model.Cat
import kotlinx.coroutines.flow.Flow

interface CatUseCase {
    fun getAllBreed(): Flow<Resource<List<Breed>>>
    fun getCatImageBreed(breedsId: String): Flow<Resource<List<Cat>>>
    fun getFavoriteCat(): Flow<List<Breed>>
    fun setCatBreedId(cat: List<Cat>?, breedsId: String)
    fun setFavoriteCat(cat: Breed, state: Boolean)
}