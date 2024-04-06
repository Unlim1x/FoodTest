package ru.unlim1x.foodtest.presentation.ui.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.unlim1x.domain.enteties.Category
import ru.unlim1x.domain.enteties.Product
import ru.unlim1x.domain.usecases.CacheLoadedCategoriesUseCase
import ru.unlim1x.domain.usecases.CacheLoadedProductsUseCase
import ru.unlim1x.domain.usecases.LoadCategoriesFromStorageUseCase
import ru.unlim1x.domain.usecases.LoadCategoriesUseCase
import ru.unlim1x.domain.usecases.LoadProductsFromStorageUseCase
import ru.unlim1x.domain.usecases.LoadProductsUseCase

class MainViewModel(
    private val loadProductsUseCase: LoadProductsUseCase,
    private val loadCategoriesUseCase: LoadCategoriesUseCase,
    private val loadProductsFromStorageUseCase: LoadProductsFromStorageUseCase,
    private val loadCategoriesFromStorageUseCase: LoadCategoriesFromStorageUseCase,
    private val cacheLoadedCategoriesUseCase: CacheLoadedCategoriesUseCase,
    private val cacheLoadedProductsUseCase: CacheLoadedProductsUseCase
) : ViewModel() {

    private val listOfProducts: MutableLiveData<List<Product>> = MutableLiveData()
    private val listOfCategories: MutableLiveData<List<Category>> = MutableLiveData()
    private val errorLD: MutableLiveData<Boolean> = MutableLiveData(false)


    val productsList: LiveData<List<Product>> get() = listOfProducts
    val categoriesList: LiveData<List<Category>> get() = listOfCategories

    val errorWhileLoading: LiveData<Boolean> get() = errorLD


    private fun loadProducts() {

        val disposable = loadProductsUseCase.execute()
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.newThread())
            .subscribe({
                Log.e("AAA", "OnNextCall")
                if (listOfProducts.value.isNullOrEmpty()) {
                    listOfProducts.postValue(it)
                    cacheProducts(it)
                }
                errorLD.postValue(false)
            },
                {
                    loadProductsFromStorage()
                    Log.e("AAA", "ErrorOccured")
                })


    }

    private fun loadProductsFromStorage() {
        val disposable = loadProductsFromStorageUseCase.execute()
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.newThread())
            .subscribe({
                if (listOfProducts.value.isNullOrEmpty()) {
                    if (it.isEmpty())
                        errorLD.postValue(true)
                    else
                        listOfProducts.postValue(it)
                }

            },
                {
                    errorLD.postValue(true)
                })
    }

    private fun cacheProducts(list: List<Product>) {
        viewModelScope.launch(Dispatchers.IO) {
            cacheLoadedProductsUseCase.execute(list)
        }
    }

    private fun loadCategories() {
        val disposable = loadCategoriesUseCase.execute()
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.newThread())
            .subscribe({
                if (listOfCategories.value.isNullOrEmpty()){
                    val list :MutableList<Category> = mutableListOf()
                    val category = Category("0", "All")
                    list.add(category)
                    list.addAll(it)
                    listOfCategories.postValue(list)
                cacheCategories(list)
                errorLD.postValue(false)}
            },
                {
                    loadCategoriesFromStorage()
                })

    }

    private fun loadCategoriesFromStorage() {
        val disposable = loadCategoriesFromStorageUseCase.execute()
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.newThread())
            .subscribe({
                if (listOfCategories.value.isNullOrEmpty()) {
                    if (it.isEmpty())
                        errorLD.postValue(true)
                    else
                        listOfCategories.postValue(it)
                }

            },
                {
                    errorLD.postValue(true)
                })
    }

    private fun cacheCategories(list: List<Category>) {
        viewModelScope.launch(Dispatchers.IO) {
            cacheLoadedCategoriesUseCase.execute(list)
        }
    }

    fun load() {
        loadProducts()
        loadCategories()
    }

}