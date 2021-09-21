package com.coherentsolutions.by.max.sir.androidtrainingtasks.persistence

import com.coherentsolutions.by.max.sir.androidtrainingtasks.home.entities.UserResponse

interface UserPersistence {
    suspend fun add(userResponse: UserResponse)
    suspend fun get(username: String): UserResponse
    suspend fun delete(username: String)
}