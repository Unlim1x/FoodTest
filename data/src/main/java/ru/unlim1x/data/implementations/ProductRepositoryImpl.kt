package ru.unlim1x.shelf.data.implementations

import android.util.Log
import io.reactivex.rxjava3.core.Observable
import ru.unlim1x.domain.enteties.Category
import ru.unlim1x.domain.enteties.Product
import ru.unlim1x.domain.interfaces.ProductRepository
import ru.unlim1x.shelf.data.retrofit.RetrofitClient


class ProductRepositoryImpl : ProductRepository {

    override fun loadProducts(): Observable<List<Product>> {
        val observableListProduct = RetrofitClient.get().loadProducts()
            .map{
                Log.e("response", "${it.meals.size}")
                val mutableList: MutableList<Product> = mutableListOf()
                it.meals.forEach {prod->
                    val listIngr:MutableList<String?> = mutableListOf()
                    prod.strIngredient1?.let{str->listIngr.add(str)}
                    prod.strIngredient2?.let{str->listIngr.add(str)}
                    prod.strIngredient3?.let{str->listIngr.add(str)}
                    prod.strIngredient4?.let{str->listIngr.add(str)}
                    prod.strIngredient5?.let{str->listIngr.add(str)}
                    prod.strIngredient6?.let{str->listIngr.add(str)}
                    prod.strIngredient7?.let{str->listIngr.add(str)}
                    val product = Product(idMeal =  prod.idMeal, strMeal =  prod.strMeal,
                        strCategory =  prod.strCategory, strMealThumb = prod.strMealThumb,
                        ingredientsList =  listIngr)
                    mutableList.add(product)
                }
                val res:List<Product> = mutableList
                res
            }
        return observableListProduct
    }



    override fun loadCategories(): Observable<List<Category>> {
        val observableListCategories = RetrofitClient.get().getCategories()
            .map{
                Log.e("response cat", "${it.categories.size}")
                val mutableList: MutableList<Category> = mutableListOf()
                it.categories.forEach {category->
                   val cat = Category(idCategory =  category.idCategory,
                                        strCategory = category.strCategory)
                    mutableList.add(cat)
                }
                val res:List<Category> = mutableList
                res
            }
        return observableListCategories
    }

}