package com.coherentsolutions.by.max.sir.androidtrainingtasks.home.user

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.coherentsolutions.by.max.sir.androidtrainingtasks.MyApplication
import com.coherentsolutions.by.max.sir.androidtrainingtasks.MyApplication.Companion.uiScope
import com.coherentsolutions.by.max.sir.androidtrainingtasks.home.entities.User
import com.coherentsolutions.by.max.sir.androidtrainingtasks.network.PetstoreService.API_KEY
import com.coherentsolutions.by.max.sir.androidtrainingtasks.network.PetstoreService.SERVER_TAG
import com.coherentsolutions.by.max.sir.androidtrainingtasks.network.RetrofitService
import com.coherentsolutions.by.max.sir.androidtrainingtasks.network.ServerStatusResponse
import com.coherentsolutions.by.max.sir.androidtrainingtasks.persistence.PetstorePersistence
import com.coherentsolutions.by.max.sir.androidtrainingtasks.persistence.UserPersistence
import com.coherentsolutions.by.max.sir.androidtrainingtasks.service.persistence
import com.coherentsolutions.by.max.sir.androidtrainingtasks.service.service
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserViewModel : ViewModel() {

    private val petstorePersistence = persistence<PetstorePersistence>()
    private lateinit var persistence: UserPersistence

    val user by lazyOf(MutableLiveData<User>())

    val eventDeleteUser by lazyOf(MutableLiveData<State>())

    companion object {
        enum class State {
            NON_CALLED_DELETE_EVENT,
            DELETE_SUCCEED,
            DELETE_FAILED
        }
    }


    init {
        Log.i(MyApplication.INFO_TAG, "INIT USER VIEW MODEL CALLED")
        val loadedUser = petstorePersistence.loadUser()
        user.value = loadedUser
        updateUserAfterSignIn()
        // persistence= persistence<UserPersistence>()
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

    /**
     * @delete from retrofit service but swagger has some bugs
     */

    fun deleteUser(username: String? = null) {
        val service = service<RetrofitService>()
        Log.i(SERVER_TAG, "DELETE USERNAME: ${username ?: user.value?.username!!}")
        val response = service.deleteUser(API_KEY, username ?: user.value?.username!!)
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
                    }
                    400, 404 -> {
                        Log.i(SERVER_TAG, "DELETE 40x BAD")
                        eventDeleteUser.value = State.DELETE_FAILED
                    }
                    else -> {
                        Log.i(SERVER_TAG, "${response.body()?.code}")
                        //throw IllegalArgumentException("No such documented code")
                        eventDeleteUser.value=State.NON_CALLED_DELETE_EVENT
                    }
                }

            }

            override fun onFailure(call: Call<ServerStatusResponse>, t: Throwable) {
                Log.i(SERVER_TAG, "DELETE FAILED user ${t.message}")
                eventDeleteUser.value = State.DELETE_FAILED

            }
        })


    }

    fun deleteEventEnded() {
        eventDeleteUser.value = State.NON_CALLED_DELETE_EVENT
    }

    fun delete(username: String) {
        uiScope.launch {
            persistence.delete(username)
        }
    }


}