package ru.unlim1x.domain.usecases

import io.reactivex.rxjava3.core.Observable
import ru.unlim1x.domain.enteties.Category
import ru.unlim1x.domain.interfaces.LocalStorage

class LoadCategoriesFromStorageUseCase(private val localStorage: LocalStorage) {
    fun execute():Observable<List<Category>>{
        return localStorage.loadCategories()
    }
}