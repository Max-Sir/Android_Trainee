package com.coherentsolutions.by.max.sir.androidtrainingtasks.home.user

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.coherentsolutions.by.max.sir.androidtrainingtasks.MyApplication
import com.coherentsolutions.by.max.sir.androidtrainingtasks.MyApplication.Companion.API_KEY
import com.coherentsolutions.by.max.sir.androidtrainingtasks.MyApplication.Companion.SERVER_TAG
import com.coherentsolutions.by.max.sir.androidtrainingtasks.home.entities.User
import com.coherentsolutions.by.max.sir.androidtrainingtasks.network.RetrofitService
import com.coherentsolutions.by.max.sir.androidtrainingtasks.regestrationmodule.ui.login.service.service
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserViewModel(user: User) : ViewModel() {

    private val _user = MutableLiveData<User>()
    val user: LiveData<User>
        get() = _user

    init {
        Log.i(MyApplication.INFO_TAG, "INIT USER VIEW MODEL CALLED")
        _user.value = user
    }

    fun updateUserAfterSignIn() {
        val service = service<RetrofitService>()
        val response = service.getUser(API_KEY,_user.value!!.username)
        response.enqueue(object: Callback<User?>{
            override fun onResponse(call: Call<User?>, response: Response<User?>) {
                Log.d(SERVER_TAG,"@GET method RESPONSE Successful ${response.body()!!}")
                _user.value=response.body()
            }

            override fun onFailure(call: Call<User?>, t: Throwable) {
                Log.d(SERVER_TAG,"@GET method RESPONSE Failure\n${t.message}")
            }
        })
    }


}