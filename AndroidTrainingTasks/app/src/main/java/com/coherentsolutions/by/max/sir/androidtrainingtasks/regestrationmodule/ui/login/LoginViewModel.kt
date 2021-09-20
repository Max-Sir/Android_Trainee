package com.coherentsolutions.by.max.sir.androidtrainingtasks.regestrationmodule.ui.login


import android.util.Log
import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.coherentsolutions.by.max.sir.androidtrainingtasks.MyApplication.Companion.INFO_TAG
import com.coherentsolutions.by.max.sir.androidtrainingtasks.MyApplication.Companion.uiScope
import com.coherentsolutions.by.max.sir.androidtrainingtasks.R
import com.coherentsolutions.by.max.sir.androidtrainingtasks.data.LoginRepository
import com.coherentsolutions.by.max.sir.androidtrainingtasks.data.Result
import com.coherentsolutions.by.max.sir.androidtrainingtasks.home.entities.User
import com.coherentsolutions.by.max.sir.androidtrainingtasks.network.PetstoreService.API_KEY
import com.coherentsolutions.by.max.sir.androidtrainingtasks.network.PetstoreService.SERVER_TAG
import com.coherentsolutions.by.max.sir.androidtrainingtasks.network.RetrofitService
import com.coherentsolutions.by.max.sir.androidtrainingtasks.network.ServerStatusResponse
import com.coherentsolutions.by.max.sir.androidtrainingtasks.persistence.PetstorePersistence
import com.coherentsolutions.by.max.sir.androidtrainingtasks.service.persistence
import com.coherentsolutions.by.max.sir.androidtrainingtasks.service.service
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class LoginViewModel(private val loginRepository: LoginRepository) : ViewModel() {

    private val _loginForm = MutableLiveData<LoginFormState>()
    val loginFormState: LiveData<LoginFormState> = _loginForm

    // val persistence= persistence<UserPersistence>()

    val petstorePersistence = persistence<PetstorePersistence>()

    private val _loginResult = MutableLiveData<LoginResult>()
    val loginResult: LiveData<LoginResult> = _loginResult

    fun login(
        username: String,
        password: String,
        email: String,
        phone: String,
        firstname: String,
        lastname: String
    ) {
        // can be launched in a separate asynchronous job
        val result = loginRepository.login(username, password, email, phone, firstname, lastname)

        Log.i(INFO_TAG, "LOGIN VIEW MODEL FUN")
        if (result is Result.Success) {
            Log.i(INFO_TAG, "LOGIN VIEW MODEL FUN FINISHED SUCCESSFULLY")
            _loginResult.value =
                LoginResult(success = LoggedInUserView(displayName = result.data.displayName))

        } else {
            Log.i(INFO_TAG, "LOGIN VIEW MODEL FUN FINISHED WITH FAILED CASE")
            _loginResult.value = LoginResult(error = R.string.login_failed)
        }
        Log.i(INFO_TAG, "LOGIN VIEW MODEL FUN FINISHED")
    }

    fun loginDataChanged(
        username: String,
        password: String,
        email: String,
        phone: String,
        firstname: String,
        lastname: String
    ) {
        when {
            !isUserNameValid(username) -> {
                _loginForm.value = LoginFormState(
                    usernameError = R.string.invalid_username
                )
            }
            !isPasswordValid(password) -> {
                _loginForm.value = LoginFormState(
                    passwordError = R.string.invalid_password
                )
            }
            !isEmailValid(email) -> {
                _loginForm.value = LoginFormState(
                    emailError = R.string.invalid_email
                )
            }
            !isLastNameValid(lastname) -> {
                _loginForm.value = LoginFormState(
                    lastnameError = R.string.invalid_lastname
                )
            }
            !isFirstNameValid(firstname) -> {
                _loginForm.value = LoginFormState(
                    firstnameError = R.string.invalid_firstname
                )
            }

            !isPhoneValid(phone) -> {
                _loginForm.value = LoginFormState(phoneError = R.string.invalid_phone_number)
            }

            else -> {
                _loginForm.value = LoginFormState(
                    isDataValid = true
                )
            }
        }
        Log.i(INFO_TAG, "LOGIN DATA CHANGED VIEW MODEL FUN FINISHED")
    }

    /**
     * @POST usage
     */
    fun postUser(user: User) {
        val retrofitService = service<RetrofitService>()
        val x = retrofitService.createUser(API_KEY, user)
        x.enqueue(object : Callback<ServerStatusResponse> {
            override fun onResponse(
                call: Call<ServerStatusResponse>,
                response: Response<ServerStatusResponse>
            ) {
                Log.d(SERVER_TAG, "POSTED $user")
                Log.d(SERVER_TAG, "GOOD REQUEST ${response.body().toString()}")
                // addUser(user)
                saveUserToPersistence(user)
            }

            override fun onFailure(call: Call<ServerStatusResponse>, t: Throwable) {
                Log.d(SERVER_TAG, "BAD REQUEST ${t.message}")
            }
        })
    }

//    fun addUser(user:User){
//        uiScope.launch {
//            persistence.add(user)
//        }
//    }

    //may be thick place of the app
    fun saveUserToPersistence(user: User) {
        uiScope.launch {
            withContext(Dispatchers.IO) {
                petstorePersistence.saveUser(user)
            }
        }
    }

    private fun isLastNameValid(lastname: String): Boolean {
        return lastname != ""
    }

    private fun isFirstNameValid(firstname: String): Boolean {
        return firstname != ""
    }

    private fun isPhoneValid(phone: String): Boolean {
        Log.i(INFO_TAG, "PHONE VALIDATION CALL")
        return Patterns.PHONE.matcher(phone).matches()
    }

    private fun isEmailValid(email: String): Boolean {
        Log.i(INFO_TAG, "EMAIL VALIDATION CALL")
        return Patterns.EMAIL_ADDRESS.matcher(email).matches() || true
    }


    // A placeholder username validation check
    fun isUserNameValid(username: String): Boolean {
        Log.i(INFO_TAG, "LOGIN FORM `USERNAME` VALIDATION")
        return if (username.contains('@')) {
            Patterns.EMAIL_ADDRESS.matcher(username).matches()
        } else {
            username.isNotBlank() && username.toList()
                .any {
                    it in 'A'..'Z'
                            || it in 'a'..'z'
                }
                    && username.toList()
                .all {
                    it in 'A'..'Z'
                            || it in 'a'..'z'
                            || it in '0'..'9'
                            || it in "$.@   #%_-"
                }
        }
    }

    // A placeholder password validation check
    fun isPasswordValid(password: String): Boolean {
        Log.i(INFO_TAG, "LOGIN SCREEN `PASSWORD VALIDATION`")
        return password.length >= 8
                && password.all { it in '!'..'~' }
                && password.any { it !in 'A'..'Z' && it !in 'a'..'z' && it !in '0'..'9' }
                && password.any { it in 'A'..'Z' }
                && password.any { it in '0'..'9' }
                && password.any { it in 'a'..'z' } || true

    }
}