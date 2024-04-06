package ru.unlim1x.foodtest.di

import org.koin.dsl.module
import ru.unlim1x.data.Base
import ru.unlim1x.data.implementations.LocalStorageImpl
import ru.unlim1x.data.room.dao.StorageDao
import ru.unlim1x.domain.interfaces.LocalStorage
import ru.unlim1x.domain.interfaces.ProductRepository
import ru.unlim1x.shelf.data.implementations.ProductRepositoryImpl

val dataModule = module {
    single<ProductRepository> {
        ProductRepositoryImpl()
    }

    single<LocalStorage> {
        LocalStorageImpl(get())
    }

    single<Base>{
        Base(get())
    }

    single<StorageDao>{
        Base(get()).getDao()
    }

}