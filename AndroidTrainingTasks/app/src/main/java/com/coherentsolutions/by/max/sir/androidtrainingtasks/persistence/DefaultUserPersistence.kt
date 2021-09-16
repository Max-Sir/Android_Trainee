package com.coherentsolutions.by.max.sir.androidtrainingtasks.persistence

import android.content.Context
import com.coherentsolutions.by.max.sir.androidtrainingtasks.database.UserDatabase
import com.coherentsolutions.by.max.sir.androidtrainingtasks.home.entities.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class DefaultUserPersistence(val context: Context):UserPersistence {

    private val database by lazyOf(UserDatabase.getInstance(context).userDao)

    override suspend fun add(user: User) {
        withContext(Dispatchers.IO){
            database.add(user)
        }
    }

    override suspend fun get(username: String): User {
        return withContext(Dispatchers.IO){
            database.get(username)
        }
    }

    override suspend fun delete(username: String) {
        withContext(Dispatchers.IO){
            database.delete(username)
        }
    }
}