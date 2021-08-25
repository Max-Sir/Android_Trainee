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


    private val _showPasswordEvent=MutableLiveData<Boolean>()
    val showPasswordEvent:LiveData<Boolean>
    get()=_showPasswordEvent

    init{
        _user.value=user
        _showPasswordEvent.value=false
    }

    override fun onCleared() {
        super.onCleared()

    }

    fun onShowPasswordEvent(){
        _showPasswordEvent.value=true
    }

    fun onHidePasswordEvent(){
        _showPasswordEvent.value=false
    }





}