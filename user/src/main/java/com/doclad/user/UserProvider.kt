package com.doclad.user

interface UserProvider {
    fun userDataRepository(): UserDataRepository
    fun userManager(): UserManager
}