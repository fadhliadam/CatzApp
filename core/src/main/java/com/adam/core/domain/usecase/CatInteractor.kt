package com.adam.core.domain.usecase

import com.adam.core.data.Resource
import com.adam.core.domain.model.Breed
import com.adam.core.domain.model.Cat
import com.adam.core.domain.repository.ICatRepository
import kotlinx.coroutines.flow.Flow

class CatInteractor(private val catRepository: ICatRepository): CatUseCase {
    override fun getAllBreed(): Flow<Resource<List<Breed>>> = catRepository.getAllBreed()

    override fun getCatImageBreed(breedsId: String): Flow<Resource<List<Cat>>> = catRepository.getCatImageBreed(breedsId)

    override fun getFavoriteCat(): Flow<List<Breed>> = catRepository.getFavoriteCat()

    override fun setCatBreedId(cat: List<Cat>?, breedsId: String) = catRepository.setCatBreedId(cat, breedsId)

    override fun setFavoriteCat(cat: Breed, state: Boolean) = catRepository.setFavoriteCat(cat, state)

}