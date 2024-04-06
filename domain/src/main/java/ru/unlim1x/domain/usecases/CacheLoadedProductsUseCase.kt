package ru.unlim1x.domain.usecases

import ru.unlim1x.domain.enteties.Product
import ru.unlim1x.domain.interfaces.LocalStorage

class CacheLoadedProductsUseCase(private val localStorage: LocalStorage) {
    suspend fun execute(listProducts: List<Product>):Boolean{
        return localStorage.cacheProducts(listProducts)
    }
}