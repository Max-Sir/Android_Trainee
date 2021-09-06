package com.coherentsolutions.by.max.sir.androidtrainingtasks.regestrationmodule.ui.login

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.room.PrimaryKey
import com.coherentsolutions.by.max.sir.androidtrainingtasks.MyApplication.Companion.INFO_TAG
import com.coherentsolutions.by.max.sir.androidtrainingtasks.R
import com.coherentsolutions.by.max.sir.androidtrainingtasks.databinding.ActivityLoginBinding
import com.coherentsolutions.by.max.sir.androidtrainingtasks.home.HomeActivity
import com.coherentsolutions.by.max.sir.androidtrainingtasks.home.UserPersistance
import com.coherentsolutions.by.max.sir.androidtrainingtasks.home.entities.User
import com.coherentsolutions.by.max.sir.androidtrainingtasks.regestrationmodule.ui.login.service.persistence
import kotlinx.android.synthetic.main.activity_login.*
import kotlin.random.Random


class LoginActivity : AppCompatActivity() {


    private lateinit var loginViewModel: LoginViewModel
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.i(INFO_TAG, "LOGIN ACTIVITY ON CREATE()")
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Log.i(INFO_TAG, "LOGIN ACTIVITY INFLATED")
        val username = binding.username
        val password = binding.password
        val login = binding.login
        val loading = binding.loading
        val email = binding.emailEditLogin
        val phone = binding.phoneEditLogin

        loginViewModel = ViewModelProvider(this, LoginViewModelFactory())
            .get(LoginViewModel::class.java)

        loginViewModel.loginFormState.observe(this@LoginActivity, Observer {
            val loginState = it ?: return@Observer

            // disable login button unless both username / password is valid
            login.isEnabled = loginState.isDataValid

            if (loginState.usernameError != null) {
                username.error = getString(loginState.usernameError)
            }
            if (loginState.passwordError != null) {
                password.error = getString(loginState.passwordError)
            }
            if(loginState.phoneError != null){
                phone.error=getString(loginState.phoneError)
            }
            if(loginState.emailError != null){
                email.error=getString(loginState.emailError)
            }
        })

        loginViewModel.loginResult.observe(this@LoginActivity, Observer {
            val loginResult = it ?: return@Observer

            loading.visibility = View.GONE
            if (loginResult.error != null) {
                showLoginFailed(loginResult.error)
            }
            if (loginResult.success != null) {
                updateUiWithUser(loginResult.success)
            }
            setResult(RESULT_OK)

            //Complete and destroy login activity once successful

            Log.i(INFO_TAG, "LOGIN ACTIVITY - USER SAVE() TO SHARED PREF BY PERSISTENCE")
            val persistence = persistence<UserPersistance>()
            persistence.saveUser(
                User(
                    email = "${binding.emailEditLogin.text}",
                    phone = "${binding.phoneEditLogin.text}",
                    userStatus = 0,
                    id = Random.nextInt(),
                    lastName = "${binding.lastnameEditLogin.text}",
                    firstName = "${binding.firstnameEditLogin.text}",
                    username = "${binding.username.text}",
                    password = "${binding.password.text}"
                )
            )
            Log.i(
                INFO_TAG,
                "LOGIN ACTIVITY - USER SAVED TO SHARED PREF BY PERSISTENCE SUCCESSFULLY"
            )


            val intent =
                Intent(this, HomeActivity::class.java)
            Log.i(INFO_TAG, "LOGIN ACTIVITY - INTENT SUCCESSFULLY INSTATED")

            startActivity(intent)
            Log.i(INFO_TAG, "LOGIN ACTIVITY - STARTED ACTIVITY ${HomeActivity::class.simpleName}")

        })

        username.afterTextChanged {
            loginViewModel.loginDataChanged(
                username.text.toString(),
                password.text.toString(),
                email_edit_login.text.toString(),
                phone_edit_login.text.toString()
            )
        }

        email.afterTextChanged {
            loginViewModel.loginDataChanged(
                username.text.toString(),
                password.text.toString(),
                email_edit_login.text.toString(),
                phone_edit_login.text.toString()
            )
        }

        phone.afterTextChanged {
            loginViewModel.loginDataChanged(
                username.text.toString(),
                password.text.toString(),
                email_edit_login.text.toString(),
                phone_edit_login.text.toString()
            )
        }

        password.apply {
            afterTextChanged {
                loginViewModel.loginDataChanged(
                    username.text.toString(),
                    password.text.toString(),
                    email_edit_login.text.toString(),
                    phone_edit_login.text.toString()
                )
            }


            setOnEditorActionListener { _, actionId, _ ->
                when (actionId) {
                    EditorInfo.IME_ACTION_DONE ->
                        loginViewModel.login(
                            username.text.toString(),
                            password.text.toString(),
                            email.text.toString(),
                            phone.text.toString()
                        )
                }
                false
            }

            login.setOnClickListener {
                loading.visibility = View.VISIBLE
                loginViewModel.login(
                    username.text.toString(),
                    password.text.toString(),
                    email.text.toString(),
                    phone.text.toString()
                )
            }
        }
    }

    private fun updateUiWithUser(model: LoggedInUserView) {
        val welcome = getString(R.string.welcome)
        // TODO : initiate successful logged in experience
        Toast.makeText(
            applicationContext,
            "$welcome ${binding.username.text ?: model.displayName}",
            Toast.LENGTH_LONG
        ).show()
    }

    private fun showLoginFailed(@StringRes errorString: Int) {
        Toast.makeText(applicationContext, errorString, Toast.LENGTH_SHORT).show()
    }
}

/**
 * Extension function to simplify setting an afterTextChanged action to EditText components.
 */
fun EditText.afterTextChanged(afterTextChanged: (String) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(editable: Editable?) {
            afterTextChanged.invoke(editable.toString())
        }

        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
    })
}