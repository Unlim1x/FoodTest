package ru.unlim1x.domain.usecases

import io.reactivex.rxjava3.core.Observable
import ru.unlim1x.domain.enteties.Category

import ru.unlim1x.domain.interfaces.ProductRepository

class LoadCategoriesUseCase(private val productRepository: ProductRepository) {
    fun execute(): Observable<List<Category>> {
        return productRepository.loadCategories()
    }
}