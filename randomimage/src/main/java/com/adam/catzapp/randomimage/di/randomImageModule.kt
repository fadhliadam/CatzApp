package com.adam.catzapp.randomimage.di

import com.adam.catzapp.randomimage.RandomImageViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val randomImageModule = module{
    viewModel { RandomImageViewModel(get()) }
}