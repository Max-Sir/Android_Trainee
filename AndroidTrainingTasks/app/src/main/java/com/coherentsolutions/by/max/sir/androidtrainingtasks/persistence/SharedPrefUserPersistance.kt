package com.coherentsolutions.by.max.sir.androidtrainingtasks.persistence

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.util.Log
import com.coherentsolutions.by.max.sir.androidtrainingtasks.MyApplication
import com.coherentsolutions.by.max.sir.androidtrainingtasks.home.HomeActivity.Companion.USER
import com.coherentsolutions.by.max.sir.androidtrainingtasks.home.entities.User
import com.google.gson.Gson

class SharedPrefUserPersistance(val context: Context) : UserPersistance {


    init{
        Log.i(MyApplication.INFO_TAG, "User persistence instantiated")
    }

    override fun loadUser():User {
        val preferences = context.getSharedPreferences(USER, MODE_PRIVATE)
        val user: User =
            Gson().fromJson(preferences.getString(USER, ""), User::class.java) as User
        Log.i(MyApplication.INFO_TAG, "User loaded")

        return user
    }


    override fun saveUser(user: User) {
        val preferences = context.getSharedPreferences(USER, MODE_PRIVATE)
        val edit = preferences.edit()
        val gson = Gson().toJson(user)
        edit.putString(USER, gson).apply()
        Log.i(MyApplication.INFO_TAG, "User saved in persistence")
    }
}