package com.coherentsolutions.by.max.sir.androidtrainingtasks.home.user

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.coherentsolutions.by.max.sir.androidtrainingtasks.home.entities.User
import java.lang.IllegalArgumentException

@Suppress("UNCHECKED_CAST")
class UserViewModelFactory(var user:User):ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(UserViewModel::class.java)){
            return UserViewModel(user) as T
        }
        throw IllegalArgumentException("Unavailable ViewModel class")
    }
}