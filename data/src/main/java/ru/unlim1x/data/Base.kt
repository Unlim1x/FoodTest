package ru.unlim1x.data

import android.content.Context
import androidx.room.Room
import ru.unlim1x.data.room.DataDataBase
import ru.unlim1x.data.room.dao.StorageDao

class Base(private val applicationContext: Context) {



    private val appDatabase: DataDataBase by lazy {
        Room.databaseBuilder(applicationContext, DataDataBase::class.java, "database.db")
            .build()
    }

    fun getDao():StorageDao{
        return appDatabase.getDao()
    }
}