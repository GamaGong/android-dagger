package com.example.android.dagger.di

import android.content.Context
import com.doclad.storage.di.StorageComponent
import com.doclad.user.UserProvider
import com.doclad.user.di.UserComponent
import com.example.android.dagger.login.LoginActivity
import com.example.android.dagger.main.MainActivity
import com.example.android.dagger.registration.RegistrationComponent
import com.example.android.dagger.settings.SettingsActivity
import dagger.Component

@Component(dependencies = [UserProvider::class], modules = [AppSubcomponents::class])
abstract class AppComponent {
    companion object {
        @Volatile
        private var instance: AppComponent? = null

        fun init(context: Context): AppComponent = instance ?: synchronized(this) {
            instance ?: kotlin.run {
                val storageProvider = StorageComponent.init(context)
                val userProvider = UserComponent.init(storageProvider)
                DaggerAppComponent.factory().create(
                    userProvider
                )
            }
        }
    }

    @Component.Factory
    interface Factory {
        fun create(
            userProvider: UserProvider
        ): AppComponent
    }

    abstract fun inject(settingsActivity: SettingsActivity)
    abstract fun inject(mainActivity: MainActivity)
    abstract fun inject(loginActivity: LoginActivity)
    abstract fun registration(): RegistrationComponent.Factory
}