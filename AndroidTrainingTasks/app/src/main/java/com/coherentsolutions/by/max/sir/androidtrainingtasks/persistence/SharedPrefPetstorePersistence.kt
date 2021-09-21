package com.coherentsolutions.by.max.sir.androidtrainingtasks.persistence

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.util.Log
import com.coherentsolutions.by.max.sir.androidtrainingtasks.MyApplication
import com.coherentsolutions.by.max.sir.androidtrainingtasks.home.HomeActivity.Companion.USER
import com.coherentsolutions.by.max.sir.androidtrainingtasks.home.entities.UserResponse
import com.google.gson.Gson

class SharedPrefPetstorePersistence(val context: Context) : PetstorePersistence {


    init {
        Log.i(MyApplication.INFO_TAG, "User persistence instantiated")
    }

    override fun loadUser(): UserResponse {
        val preferences = context.getSharedPreferences(USER, MODE_PRIVATE)
        val userResponse: UserResponse =
            Gson().fromJson(preferences.getString(USER, ""), UserResponse::class.java) as UserResponse
        Log.i(MyApplication.INFO_TAG, "User loaded")

        return userResponse
    }


    override fun saveUser(userResponse: UserResponse) {
        val preferences = context.getSharedPreferences(USER, MODE_PRIVATE)
        val edit = preferences.edit()
        val gson = Gson().toJson(userResponse)
        edit.putString(USER, gson).apply()
        Log.i(MyApplication.INFO_TAG, "User saved in persistence")
    }
}