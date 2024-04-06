package ru.unlim1x.domain.enteties

data class Product(
    val idMeal : String?, val strMeal : String?, val strCategory : String?,
    val strMealThumb : String?, val ingredientsList: List<String?>
)
