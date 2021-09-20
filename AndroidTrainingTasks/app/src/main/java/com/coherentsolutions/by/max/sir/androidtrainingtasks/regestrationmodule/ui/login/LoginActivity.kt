package com.coherentsolutions.by.max.sir.androidtrainingtasks.regestrationmodule.ui.login

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
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
import com.coherentsolutions.by.max.sir.androidtrainingtasks.MyApplication.Companion.INFO_TAG
import com.coherentsolutions.by.max.sir.androidtrainingtasks.R
import com.coherentsolutions.by.max.sir.androidtrainingtasks.databinding.ActivityLoginBinding
import com.coherentsolutions.by.max.sir.androidtrainingtasks.home.HomeActivity
import com.coherentsolutions.by.max.sir.androidtrainingtasks.home.entities.User
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
        val firstname = binding.firstnameEditLogin
        val lastname = binding.lastnameEditLogin

        loginViewModel = ViewModelProvider(this, LoginViewModelFactory())
            .get(LoginViewModel::class.java)

        loginViewModel.loginFormState.observe(this@LoginActivity, Observer {
            val loginState = it ?: return@Observer

            // disable login button unless both username / password is valid
            login.isEnabled = loginState.isDataValid

            when {
                loginState.usernameError != null -> {
                    username.error = getString(loginState.usernameError)
                }
                loginState.firstnameError != null -> {
                    firstname.error = getString(loginState.firstnameError)
                }
                loginState.lastnameError != null -> {
                    lastname.error = getString(loginState.lastnameError)
                }
                loginState.passwordError != null -> {
                    password.error = getString(loginState.passwordError)
                }
                loginState.phoneError != null -> {
                    phone.error = getString(loginState.phoneError)
                }
                loginState.emailError != null -> {
                    email.error = getString(loginState.emailError)
                }
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

            val user = User(
                email = "${binding.emailEditLogin.text}",
                phone = "${binding.phoneEditLogin.text}",
                userStatus = 0,
                id = Random.nextInt(Int.MAX_VALUE),
                lastName = "${binding.lastnameEditLogin.text}",
                firstName = "${binding.firstnameEditLogin.text}",
                username = "${binding.username.text}",
                password = "${binding.password.text}"
            )
            if (isOnline(this))
                loginViewModel.postUser(user)
            else {
                return@Observer
            }
            Log.i(
                INFO_TAG,
                "LOGIN ACTIVITY - USER SAVED TO SHARED PREF BY PERSISTENCE SUCCESSFULLY"
            )


            val intent =
                Intent(this, HomeActivity::class.java)
            Log.i(INFO_TAG, "LOGIN ACTIVITY - INTENT SUCCESSFULLY INSTATED")

            startActivity(intent)
            Log.i(INFO_TAG, "LOGIN ACTIVITY - STARTED ACTIVITY ${HomeActivity::class.simpleName}")
            finish()

        })

        loginViewModel.actionProgressBarEvent.observe(this, {
            if (it == true) {
                loading.visibility = View.VISIBLE
            } else {
                loading.visibility = View.GONE
            }
        })

        username.afterTextChanged {
            loginViewModel.loginDataChanged(
                username.text.toString(),
                password.text.toString(),
                email.text.toString(),
                phone.text.toString(),
                firstname.text.toString(),
                lastname.text.toString()
            )
        }

        email.afterTextChanged {
            loginViewModel.loginDataChanged(
                username.text.toString(),
                password.text.toString(),
                email.text.toString(),
                phone.text.toString(),
                firstname.text.toString(),
                lastname.text.toString()
            )
        }
        firstname.afterTextChanged {
            loginViewModel.loginDataChanged(
                username.text.toString(),
                password.text.toString(),
                email.text.toString(),
                phone.text.toString(),
                firstname.text.toString(),
                lastname.text.toString()
            )
        }

        lastname.afterTextChanged {
            loginViewModel.loginDataChanged(
                username.text.toString(),
                password.text.toString(),
                email.text.toString(),
                phone.text.toString(),
                firstname.text.toString(),
                lastname.text.toString()
            )
        }


        phone.afterTextChanged {
            loginViewModel.loginDataChanged(
                username.text.toString(),
                password.text.toString(),
                email.text.toString(),
                phone.text.toString(),
                firstname.text.toString(),
                lastname.text.toString()
            )
        }

        password.apply {
            afterTextChanged {
                loginViewModel.loginDataChanged(
                    username.text.toString(),
                    password.text.toString(),
                    email.text.toString(),
                    phone.text.toString(),
                    firstname.text.toString(),
                    lastname.text.toString()
                )
            }


            setOnEditorActionListener { _, actionId, _ ->
                when (actionId) {
                    EditorInfo.IME_ACTION_DONE ->
                        loginViewModel.login(
                            username.text.toString(),
                            password.text.toString(),
                            email.text.toString(),
                            phone.text.toString(),
                            firstname.text.toString(),
                            lastname.text.toString()
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
                    phone.text.toString(),
                    firstname.text.toString(),
                    lastname.text.toString()
                )
            }
        }
    }

    private fun updateUiWithUser(model: LoggedInUserView) {
        if (isOnline(this)) {
            val welcome = getString(R.string.welcome)
            Toast.makeText(
                applicationContext,
                "$welcome ${binding.username.text ?: model.displayName}",
                Toast.LENGTH_LONG
            ).show()
        } else {
            showLoginFailed(R.string.check_internet_and_try_again)
        }
    }

    private fun showLoginFailed(@StringRes errorString: Int) {
        Toast.makeText(applicationContext, errorString, Toast.LENGTH_SHORT).show()
    }
}

fun isOnline(context: Context): Boolean {
    val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val capabilities =
        connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
    if (capabilities != null) {
        when {
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> {
                Log.i("Internet", "NetworkCapabilities.TRANSPORT_CELLULAR")
                return true
            }
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> {
                Log.i("Internet", "NetworkCapabilities.TRANSPORT_WIFI")
                return true
            }
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> {
                Log.i("Internet", "NetworkCapabilities.TRANSPORT_ETHERNET")
                return true
            }
        }
    }
    return false
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