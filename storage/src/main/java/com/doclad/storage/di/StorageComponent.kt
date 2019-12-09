package com.doclad.storage.di

import android.content.Context
import com.doclad.user.StorageProvider
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Component(modules = [StorageModule::class])
@Singleton
abstract class StorageComponent : StorageProvider {
    companion object {
        @Volatile
        private var instance: StorageComponent? = null

        fun init(context: Context): StorageComponent = instance ?: synchronized(this) {
            instance ?: DaggerStorageComponent.factory().create(context)
        }
    }

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): StorageComponent
    }
}