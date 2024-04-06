package ru.unlim1x.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.unlim1x.data.room.dao.StorageDao
import ru.unlim1x.data.room.enteties.CategoriesDB
import ru.unlim1x.data.room.enteties.ProductsDB


@Database(
    version = 1,
    entities = [
        CategoriesDB::class,
        ProductsDB::class
    ]
)
abstract class DataDataBase : RoomDatabase(){
    abstract fun getDao(): StorageDao
}