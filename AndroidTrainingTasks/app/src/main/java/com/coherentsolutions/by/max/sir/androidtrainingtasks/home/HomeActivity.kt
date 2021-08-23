package com.coherentsolutions.by.max.sir.androidtrainingtasks.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.coherentsolutions.by.max.sir.androidtrainingtasks.R
import com.coherentsolutions.by.max.sir.androidtrainingtasks.home.user.UserFragment

val LOGIN = "login"
val PASSWORD = "password"

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        val userFragment = UserFragment()
        userFragment.arguments?.apply {
            putString(LOGIN, intent.getStringExtra(LOGIN))
            putString(
                PASSWORD, intent.getStringExtra(PASSWORD)
            )
        }
    }
}