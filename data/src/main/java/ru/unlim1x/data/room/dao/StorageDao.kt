package ru.unlim1x.data.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import io.reactivex.rxjava3.core.Observable
import ru.unlim1x.data.room.enteties.CategoriesDB
import ru.unlim1x.data.room.enteties.ProductsDB


@Dao
interface StorageDao {

    @Insert(entity = ProductsDB::class)
    fun insertProduct(product: ProductsDB)

    @Insert(entity = CategoriesDB::class)
    fun insertCategory(category: CategoriesDB)

    @Query("Delete from products")
    fun clearProducts()

    @Query("Delete from categories")
    fun clearCategories()

    @Query("Select * from products")
    fun getProducts():Observable<List<ProductsDB>>

    @Query("Select * from categories")
    fun getCategories():Observable<List<CategoriesDB>>


}