package ru.unlim1x.domain.interfaces

import io.reactivex.rxjava3.core.Observable
import ru.unlim1x.domain.enteties.Category
import ru.unlim1x.domain.enteties.Product

interface LocalStorage {
    fun loadProducts():Observable<List<Product>>
    fun loadCategories():Observable<List<Category>>
    suspend fun cacheProducts(productsList:List<Product>):Boolean
    suspend fun cacheCategories(categoriesList:List<Category>):Boolean
}