package com.coherentsolutions.by.max.sir.androidtrainingtasks.home.user

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.coherentsolutions.by.max.sir.androidtrainingtasks.home.entities.User

typealias Username = String
typealias Password=String


class UserViewModel(user:User) : ViewModel() {

//    private val _username=MutableLiveData<Username>()
//    val username:LiveData<Username>
//    get()=_username
//
//    private val _password= MutableLiveData<Password>()
//    val password:LiveData<Password>
//    get()=_password

    private val _user= MutableLiveData<User>()
    val user:LiveData<User>
    get() = _user


    init{
        _user.value=user
    }

    override fun onCleared() {
        super.onCleared()

    }





}