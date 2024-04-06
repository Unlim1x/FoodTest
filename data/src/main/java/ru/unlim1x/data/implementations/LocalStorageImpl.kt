package ru.unlim1x.data.implementations

import io.reactivex.rxjava3.core.Observable
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.unlim1x.data.room.dao.StorageDao
import ru.unlim1x.data.room.enteties.CategoriesDB
import ru.unlim1x.data.room.enteties.ProductsDB
import ru.unlim1x.domain.enteties.Category
import ru.unlim1x.domain.enteties.Product
import ru.unlim1x.domain.interfaces.LocalStorage

class LocalStorageImpl(private val storageDao: StorageDao) :LocalStorage {

    override fun loadProducts(): Observable<List<Product>> {
        return storageDao.getProducts().map {
            val newList : MutableList<Product> = mutableListOf()
            it.forEach {prod->
                val product = Product(prod.idMeal, prod.strMeal, prod.strCategory, prod.strMealThumb
                ,prod.listIngredients?.trim()?.splitToSequence(' ')
                        ?.filter { it.isNotEmpty() } // or: .filter { it.isNotBlank() }
                        ?.toList()!!)
                newList.add(product)
            }
            newList
        }
    }

    override suspend fun cacheProducts(productsList: List<Product>): Boolean {
        return withContext(Dispatchers.IO) {
            storageDao.clearProducts()
            productsList.forEach {
               val prodDB = ProductsDB(it.idMeal!!, it.strMeal, it.strCategory, it.strMealThumb,
                   it.ingredientsList.joinToString())
                storageDao.insertProduct(prodDB)
            }
            return@withContext true
        }
    }

    override suspend fun cacheCategories(categoriesList: List<Category>): Boolean {
        return withContext(Dispatchers.IO) {
            storageDao.clearCategories()
            categoriesList.forEach {
                val catDB = CategoriesDB(it.idCategory!!, it.strCategory)
                storageDao.insertCategory(catDB)
            }
            return@withContext true
        }
    }

    override fun loadCategories(): Observable<List<Category>> {
        return storageDao.getCategories().map {
            val newList : MutableList<Category> = mutableListOf()
            it.forEach {cat->
                val category = Category(cat.idCategory, cat.strCategory)
                newList.add(category)
            }
            newList
        }
    }
}