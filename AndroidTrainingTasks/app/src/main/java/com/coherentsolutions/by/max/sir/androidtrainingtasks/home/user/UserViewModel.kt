package com.coherentsolutions.by.max.sir.androidtrainingtasks.home.user

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.coherentsolutions.by.max.sir.androidtrainingtasks.home.entities.User

class UserViewModel(username:String,password:String) : ViewModel() {

    val _user=MutableLiveData<User>()
    val user:LiveData<User>
    get()=_user


    init{

    }

    override fun onCleared() {
        super.onCleared()

    }
}