package com.adam.catzapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.adam.core.domain.usecase.CatUseCase

class FavouriteViewModel(catUseCase: CatUseCase) : ViewModel() {

    val favourite = catUseCase.getFavoriteCat().asLiveData()
}