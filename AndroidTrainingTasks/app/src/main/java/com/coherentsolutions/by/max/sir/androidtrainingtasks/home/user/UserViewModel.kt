package com.coherentsolutions.by.max.sir.androidtrainingtasks.home.user

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.coherentsolutions.by.max.sir.androidtrainingtasks.MyApplication
import com.coherentsolutions.by.max.sir.androidtrainingtasks.home.entities.User
import com.coherentsolutions.by.max.sir.androidtrainingtasks.network.PetstoreService.API_KEY
import com.coherentsolutions.by.max.sir.androidtrainingtasks.network.PetstoreService.SERVER_TAG
import com.coherentsolutions.by.max.sir.androidtrainingtasks.network.RetrofitService
import com.coherentsolutions.by.max.sir.androidtrainingtasks.persistence.UserPersistance
import com.coherentsolutions.by.max.sir.androidtrainingtasks.regestrationmodule.ui.login.service.persistence
import com.coherentsolutions.by.max.sir.androidtrainingtasks.regestrationmodule.ui.login.service.service
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserViewModel() : ViewModel() {

    val persistence: UserPersistance = persistence<UserPersistance>()

    val user by lazyOf(MutableLiveData<User>())

    init {
        Log.i(MyApplication.INFO_TAG, "INIT USER VIEW MODEL CALLED")
        val loadedUser = persistence.loadUser()
        user.value = loadedUser
    }

    fun updateUserAfterSignIn() {
        val service = service<RetrofitService>()
        val response = service.getUser(API_KEY, user.value!!.username)
        response.enqueue(object : Callback<User?> {
            override fun onResponse(call: Call<User?>, response: Response<User?>) {
                Log.d(SERVER_TAG, "@GET method RESPONSE Successful ${response.body()}")
                if (response.isSuccessful) {
                    user.value = response.body()
                    return
                } else {
                    updateUserAfterSignIn()
                }
            }

            override fun onFailure(call: Call<User?>, t: Throwable) {
                Log.d(SERVER_TAG, "@GET method RESPONSE Failure\n${t.message}")
            }
        })
    }


}