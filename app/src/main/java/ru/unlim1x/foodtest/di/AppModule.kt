package ru.unlim1x.foodtest.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.unlim1x.foodtest.presentation.ui.main.MainViewModel

val appModule = module {

    viewModel<MainViewModel> {
        MainViewModel(
            loadProductsUseCase = get(),
            loadCategoriesUseCase = get(),
            loadProductsFromStorageUseCase = get(),
            loadCategoriesFromStorageUseCase = get(),
            cacheLoadedProductsUseCase = get(),
            cacheLoadedCategoriesUseCase = get()
        )
    }
}