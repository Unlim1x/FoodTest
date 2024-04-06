package ru.unlim1x.shelf.data.retrofit

import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import ru.unlim1x.data.enteties.CategoryResponse
import ru.unlim1x.shelf.data.enteties.ProductResponse

interface RetrofitQueue {

    //Запрос получения товаров
    @GET("/api/json/v1/1/search.php?s")
    fun loadProducts(): Observable<ProductResponse>

    //Запрос получения категорий
    @GET("/api/json/v1/1/categories.php")
    fun getCategories(): Observable<CategoryResponse>

}