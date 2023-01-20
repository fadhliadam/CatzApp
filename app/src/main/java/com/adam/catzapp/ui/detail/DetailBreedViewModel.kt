package com.adam.catzapp.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.adam.core.domain.model.Breed
import com.adam.core.domain.model.Cat
import com.adam.core.domain.usecase.CatUseCase

class DetailBreedViewModel(private val catUseCase: CatUseCase) : ViewModel(){
    fun cat(breedsId: String) = catUseCase.getCatImageBreed(breedsId).asLiveData()

    fun setCatBreed(cat: List<Cat>?, breedsId: String) =
        catUseCase.setCatBreedId(cat, breedsId)

    fun setFavouriteCat(breed: Breed, newStatus: Boolean) =
        catUseCase.setFavoriteCat(breed, newStatus)
}