package com.coherentsolutions.by.max.sir.androidtrainingtasks.home.user

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.coherentsolutions.by.max.sir.androidtrainingtasks.MyApplication.Companion.INFO_TAG
import com.coherentsolutions.by.max.sir.androidtrainingtasks.MyApplication.Companion.uiScope
import com.coherentsolutions.by.max.sir.androidtrainingtasks.home.entities.UserResponse
import com.coherentsolutions.by.max.sir.androidtrainingtasks.network.PetstoreService.API_KEY
import com.coherentsolutions.by.max.sir.androidtrainingtasks.network.PetstoreService.SERVER_TAG
import com.coherentsolutions.by.max.sir.androidtrainingtasks.network.RetrofitService
import com.coherentsolutions.by.max.sir.androidtrainingtasks.network.ServerStatusResponse
import com.coherentsolutions.by.max.sir.androidtrainingtasks.persistence.PetstorePersistence
import com.coherentsolutions.by.max.sir.androidtrainingtasks.persistence.UserPersistence
import com.coherentsolutions.by.max.sir.androidtrainingtasks.service.ServiceLocator.DATABASE_TAG
import com.coherentsolutions.by.max.sir.androidtrainingtasks.service.persistence
import com.coherentsolutions.by.max.sir.androidtrainingtasks.service.service
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserViewModel : ViewModel() {

    val user by lazyOf(MutableLiveData<UserResponse>())

    val persistence = persistence<UserPersistence>()

    val eventDeleteUser by lazyOf(MutableLiveData<State>())

    val loadingEvent by lazyOf(MutableLiveData<Boolean>())

    companion object {
        enum class State {
            NON_CALLED_DELETE_EVENT,
            DELETE_SUCCEED,
            DELETE_FAILED
        }
    }


    init {
        Log.i(INFO_TAG, "INIT USER VIEW MODEL CALLED")
        user.value = persistence<PetstorePersistence>().loadUser()
        updateUserAfterSignIn()
        Log.i(INFO_TAG, "load from Shared Preferences")
        loadingEvent.value = false
    }

    private fun startLoadingEvent() {
        loadingEvent.value = true
    }

    private fun endLoadingEvent() {
        loadingEvent.value = false
    }


    fun updateUserAfterSignIn() {
        startLoadingEvent()
        val service = service<RetrofitService>()
        Log.i(INFO_TAG, "username")
        get(user.value!!.username)
        val response = service.getUser(API_KEY, user.value!!.username)
        response.enqueue(object : Callback<UserResponse?> {
            override fun onResponse(call: Call<UserResponse?>, response: Response<UserResponse?>) {
                Log.d(SERVER_TAG, "@GET method RESPONSE Successful ${response.body()}")
                uiScope.launch {
                    delay(400)
                    endLoadingEvent()
                }
                if (response.body() != null) {
                    user.value = response.body()
                    return
                }
            }

            override fun onFailure(call: Call<UserResponse?>, t: Throwable) {
                Log.d(SERVER_TAG, "@GET method RESPONSE Failure\n${t.message}")
                endLoadingEvent()
            }
        })
    }

    /**
     * @delete from retrofit service but swagger has some bugs
     */

    fun deleteUser(username: String? = null) {
        startLoadingEvent()
        val service = service<RetrofitService>()
        val usernameKey = username ?: user.value?.username!!
        Log.i(SERVER_TAG, "DELETE USERNAME: $username")
        delete(usernameKey)
        val response = service.deleteUser(API_KEY, usernameKey)
        response.enqueue(object : Callback<ServerStatusResponse> {
            override fun onResponse(
                call: Call<ServerStatusResponse>,
                response: Response<ServerStatusResponse>
            ) {
                Log.i(SERVER_TAG, "DELETE SUCCESSFULLY user ${user.value!!}")
                when (response.body()?.code) {
                    200 -> {
                        Log.i(SERVER_TAG, "DELETE 200 OK")
                        eventDeleteUser.value = State.DELETE_SUCCEED
                        endLoadingEvent()
                    }
                    400, 404 -> {
                        Log.i(SERVER_TAG, "DELETE 40x BAD")
                        eventDeleteUser.value = State.DELETE_FAILED
                        endLoadingEvent()
                    }
                    else -> {
                        Log.i(SERVER_TAG, "${response.body()?.code}")
                        //throw IllegalArgumentException("No such documented code")
                        eventDeleteUser.value = State.DELETE_FAILED
                        endLoadingEvent()
                    }
                }

            }

            override fun onFailure(call: Call<ServerStatusResponse>, t: Throwable) {
                Log.i(SERVER_TAG, "DELETE FAILED user ${t.message}")
                eventDeleteUser.value = State.DELETE_FAILED
                endLoadingEvent()
            }
        })

    }

    fun deleteEventEnded() {
        eventDeleteUser.value = State.NON_CALLED_DELETE_EVENT
    }

    fun get(username: String) {
        uiScope.launch {
            val user = persistence.get(username) ?: return@launch
            Log.i(DATABASE_TAG, "DATABASE GET value: $user")
            //TODO("Not yet needed")
        }
    }

    fun delete(username: String) {
        uiScope.launch {
            persistence.delete(username)
            Log.i(DATABASE_TAG, "DATABASE DELETE value : $username")
        }
    }


}