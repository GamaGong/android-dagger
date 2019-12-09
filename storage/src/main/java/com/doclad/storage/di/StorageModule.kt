package com.doclad.storage.di

import com.doclad.storage.SharedPreferencesStorage
import com.doclad.user.Storage
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
interface StorageModule {
    @Binds
    @Singleton
    fun storage(sharedPreferencesStorage: SharedPreferencesStorage): Storage
}