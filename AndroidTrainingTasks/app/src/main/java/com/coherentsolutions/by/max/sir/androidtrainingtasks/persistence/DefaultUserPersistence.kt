package com.coherentsolutions.by.max.sir.androidtrainingtasks.persistence

import com.coherentsolutions.by.max.sir.androidtrainingtasks.database.UserDao
import com.coherentsolutions.by.max.sir.androidtrainingtasks.home.entities.UserResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class DefaultUserPersistence(val database: UserDao) : UserPersistence {


    override suspend fun add(userResponse: UserResponse) {
        withContext(Dispatchers.IO) {
            database.add(userResponse)
        }
    }

    override suspend fun get(username: String): UserResponse {
        return withContext(Dispatchers.IO) {
            database.get(username)
        }
    }

    override suspend fun delete(username: String) {
        withContext(Dispatchers.IO) {
            database.delete(username)
        }
    }
}