package com.coherentsolutions.by.max.sir.androidtrainingtasks.home

import com.coherentsolutions.by.max.sir.androidtrainingtasks.home.entities.User

interface UserPersistance {
    fun loadUser(): User
    fun saveUser(user:User)
}