package ru.unlim1x.foodtest.di

import org.koin.dsl.module
import ru.unlim1x.domain.usecases.CacheLoadedCategoriesUseCase
import ru.unlim1x.domain.usecases.CacheLoadedProductsUseCase
import ru.unlim1x.domain.usecases.LoadCategoriesFromStorageUseCase
import ru.unlim1x.domain.usecases.LoadCategoriesUseCase
import ru.unlim1x.domain.usecases.LoadProductsFromStorageUseCase
import ru.unlim1x.domain.usecases.LoadProductsUseCase

val domainModule = module {
    factory<LoadProductsUseCase> {
        LoadProductsUseCase(productRepository = get())
    }

    factory<LoadCategoriesUseCase> {
        LoadCategoriesUseCase(productRepository = get())
    }

    factory<LoadCategoriesFromStorageUseCase> {
        LoadCategoriesFromStorageUseCase(localStorage = get())
    }
    factory<LoadProductsFromStorageUseCase> {
        LoadProductsFromStorageUseCase(localStorage = get())
    }
    factory<CacheLoadedCategoriesUseCase> {
        CacheLoadedCategoriesUseCase(localStorage = get())
    }
    factory<CacheLoadedProductsUseCase> {
        CacheLoadedProductsUseCase(localStorage = get())
    }

}