package com.coherentsolutions.by.max.sir.androidtrainingtasks.persistence

import com.coherentsolutions.by.max.sir.androidtrainingtasks.home.entities.UserResponse

interface PetstorePersistence {
    fun loadUser(): UserResponse
    fun saveUser(userResponse: UserResponse)
}