package com.adam.catzapp.randomimage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.adam.core.domain.usecase.CatUseCase

class RandomImageViewModel(catUseCase: CatUseCase) : ViewModel() {
    val randomImage = catUseCase.getRandomCatImage().asLiveData()
}