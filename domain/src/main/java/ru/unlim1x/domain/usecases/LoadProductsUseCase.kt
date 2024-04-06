package ru.unlim1x.domain.usecases

import io.reactivex.rxjava3.core.Observable
import ru.unlim1x.domain.enteties.Product
import ru.unlim1x.domain.interfaces.ProductRepository

class LoadProductsUseCase(private val productRepository: ProductRepository) {
    fun execute(): Observable<List<Product>> {
        return productRepository.loadProducts()
    }
}