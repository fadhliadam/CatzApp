package com.adam.catzapp.di

import com.adam.catzapp.ui.detail.DetailBreedViewModel
import com.adam.catzapp.ui.home.HomeViewModel
import com.adam.core.domain.usecase.CatInteractor
import com.adam.core.domain.usecase.CatUseCase
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<CatUseCase> { CatInteractor(get()) }
}

val viewModelModule = module {
    viewModel { HomeViewModel(get()) }
    viewModel { DetailBreedViewModel(get()) }
}