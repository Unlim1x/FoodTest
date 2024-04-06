package ru.unlim1x.data.room.enteties

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "categories")
data class CategoriesDB(
    @PrimaryKey
    val idCategory             : String,
                         val strCategory            : String?)