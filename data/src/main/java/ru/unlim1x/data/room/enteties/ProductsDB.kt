package ru.unlim1x.data.room.enteties

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "products")
data class ProductsDB(
    @PrimaryKey
    val idMeal : String, val strMeal : String?, val strCategory : String?,
    val strMealThumb : String?,
    val listIngredients:String?
)
