package com.coherentsolutions.by.max.sir.androidtrainingtasks.home

import android.content.SharedPreferences
import com.coherentsolutions.by.max.sir.androidtrainingtasks.home.entities.User
import com.google.gson.Gson

interface UserPersistance {
    fun loadUser()
    fun saveUser(user:User)
}