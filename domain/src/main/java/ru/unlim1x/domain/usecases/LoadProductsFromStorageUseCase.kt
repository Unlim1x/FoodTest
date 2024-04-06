package ru.unlim1x.domain.usecases

import io.reactivex.rxjava3.core.Observable
import ru.unlim1x.domain.enteties.Product
import ru.unlim1x.domain.interfaces.LocalStorage

class LoadProductsFromStorageUseCase(private val localStorage: LocalStorage) {
    fun execute():Observable<List<Product>>{
        return localStorage.loadProducts()
    }
}