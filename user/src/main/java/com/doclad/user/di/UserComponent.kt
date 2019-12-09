package com.doclad.user.di

import com.doclad.user.StorageProvider
import com.doclad.user.UserProvider
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(dependencies = [StorageProvider::class])
abstract class UserComponent : UserProvider {
    companion object {
        @Volatile
        private var instance: UserComponent? = null

        fun init(storageProvider: StorageProvider): UserComponent = instance ?: synchronized(this) {
            instance ?: DaggerUserComponent.factory().create(storageProvider)
        }
    }

    @Component.Factory
    interface Factory {
        fun create(storageProvider: StorageProvider): UserComponent
    }
}