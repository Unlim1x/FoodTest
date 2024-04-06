package ru.unlim1x.domain.usecases

import ru.unlim1x.domain.enteties.Category
import ru.unlim1x.domain.interfaces.LocalStorage

class CacheLoadedCategoriesUseCase(private val localStorage: LocalStorage) {
    suspend fun execute(listCategories:List<Category>):Boolean{
        return localStorage.cacheCategories(listCategories)
    }
}