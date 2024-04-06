package ru.unlim1x.domain.interfaces

import io.reactivex.rxjava3.core.Observable
import ru.unlim1x.domain.enteties.Category
import ru.unlim1x.domain.enteties.Product


interface ProductRepository {
    fun loadProducts():Observable<List<Product>>
    fun loadCategories():Observable<List<Category>>

}