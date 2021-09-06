package com.coherentsolutions.by.max.sir.androidtrainingtasks.regestrationmodule.ui.login


import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import android.util.Patterns
import com.coherentsolutions.by.max.sir.androidtrainingtasks.MyApplication.Companion.INFO_TAG
import com.coherentsolutions.by.max.sir.androidtrainingtasks.data.LoginRepository
import com.coherentsolutions.by.max.sir.androidtrainingtasks.data.Result
import com.coherentsolutions.by.max.sir.androidtrainingtasks.R


class LoginViewModel(private val loginRepository: LoginRepository) : ViewModel() {

    private val _loginForm = MutableLiveData<LoginFormState>()
    val loginFormState: LiveData<LoginFormState> = _loginForm

    private val _loginResult = MutableLiveData<LoginResult>()
    val loginResult: LiveData<LoginResult> = _loginResult

    fun login(username: String, password: String) {
        // can be launched in a separate asynchronous job
        val result = loginRepository.login(username, password)

        Log.i(INFO_TAG,"LOGIN VIEW MODEL FUN")
        if (result is Result.Success) {
            Log.i(INFO_TAG,"LOGIN VIEW MODEL FUN FINISHED SUCCESSFULLY")
            _loginResult.value =
                LoginResult(success = LoggedInUserView(displayName = result.data.displayName))

        } else {
            Log.i(INFO_TAG,"LOGIN VIEW MODEL FUN FINISHED WITH FAILED CASE")
            _loginResult.value = LoginResult(error = R.string.login_failed)
        }
        Log.i(INFO_TAG,"LOGIN VIEW MODEL FUN FINISHED")
    }

    fun loginDataChanged(username: String, password: String) {
        if (!isUserNameValid(username)) {
            _loginForm.value = LoginFormState(usernameError = R.string.invalid_username)
        } else if (!isPasswordValid(password)) {
            _loginForm.value = LoginFormState(passwordError = R.string.invalid_password)
        } else {
            _loginForm.value = LoginFormState(isDataValid = true)
        }
        Log.i(INFO_TAG,"LOGIN DATA CHANGED VIEW MODEL FUN FINISHED")
    }


    // A placeholder username validation check
    fun isUserNameValid(username: String): Boolean {
        Log.i(INFO_TAG,"LOGIN FORM `USERNAME` VALIDATION")
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
                            || it in "$.@#%_-"
                }
        }
    }

    // A placeholder password validation check
    fun isPasswordValid(password: String): Boolean {
        Log.i(INFO_TAG,"LOGIN SCREEN `PASSWORD VALIDATION`")
        return password.length >= 8
                && password.all { it in '!'..'~' }
                && password.any { it !in 'A'..'Z' && it !in 'a'..'z' && it !in '0'..'9' }
                && password.any { it in 'A'..'Z' }
                && password.any { it in '0'..'9' }
                && password.any { it in 'a'..'z' }

    }
}