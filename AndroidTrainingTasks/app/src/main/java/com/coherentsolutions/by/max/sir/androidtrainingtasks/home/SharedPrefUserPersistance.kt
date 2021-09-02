package com.coherentsolutions.by.max.sir.androidtrainingtasks.home

import android.content.SharedPreferences
import com.coherentsolutions.by.max.sir.androidtrainingtasks.home.entities.User
import com.google.gson.Gson

class SharedPrefUserPersistance(val preferences: SharedPreferences):UserPersistance{


    override fun loadUser() {
        preferences
    }


    override fun saveUser(user: User) {
        val edit= preferences.edit()
        val gson = Gson().toJson(user)
        edit.putString(HomeActivity.USER, gson).apply()
    }
}