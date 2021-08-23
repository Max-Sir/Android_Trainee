package com.coherentsolutions.by.max.sir.androidtrainingtasks.home.user

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException

@Suppress("UNCHECKED_CAST")
class UserViewModelFactory(var username:String, var password:String):ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(UserViewModel::class.java)){
            return UserViewModel(username,password) as T
        }
        throw IllegalArgumentException("Unavailable ViewModel class")
    }
}