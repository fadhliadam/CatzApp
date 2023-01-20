package com.adam.catzapp.di

import com.adam.catzapp.FavouriteViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val favouriteModule = module{
    viewModel{ FavouriteViewModel(get()) }
}