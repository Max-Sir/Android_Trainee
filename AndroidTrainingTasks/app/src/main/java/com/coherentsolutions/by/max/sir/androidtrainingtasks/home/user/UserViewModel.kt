package com.coherentsolutions.by.max.sir.androidtrainingtasks.home.user

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.coherentsolutions.by.max.sir.androidtrainingtasks.MyApplication
import com.coherentsolutions.by.max.sir.androidtrainingtasks.home.entities.User

class UserViewModel(user: User) : ViewModel() {

    private val _user = MutableLiveData<User>()
    val user: LiveData<User>
        get() = _user

    init {
        Log.i(MyApplication.INFO_TAG,"INIT USER VIEW MODEL CALLED")
        _user.value = user
    }

}