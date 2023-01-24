package com.adam.catzapp.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.adam.core.domain.usecase.CatUseCase

class HomeViewModel(catUseCase: CatUseCase) : ViewModel() {
    val breed by lazy {
        catUseCase.getAllBreed().asLiveData()
    }
}