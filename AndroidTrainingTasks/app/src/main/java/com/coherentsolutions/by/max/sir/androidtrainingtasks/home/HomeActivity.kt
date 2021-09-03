package com.coherentsolutions.by.max.sir.androidtrainingtasks.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.coherentsolutions.by.max.sir.androidtrainingtasks.R
import com.coherentsolutions.by.max.sir.androidtrainingtasks.databinding.ActivityHomeBinding
import com.coherentsolutions.by.max.sir.androidtrainingtasks.home.entities.User
import com.coherentsolutions.by.max.sir.androidtrainingtasks.home.user.UserFragment


class HomeActivity : AppCompatActivity() {

    companion object {
        const val USER = "USER"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding =
            DataBindingUtil.setContentView<ActivityHomeBinding>(this, R.layout.activity_home)

        val bottomNavigationItem = binding.bottomNavigation

        val navController = this.findNavController(R.id.myNavHostFragment)
        val appBarConfiguration = AppBarConfiguration(setOf(R.id.userFragment, R.id.petsFragment))
        setupActionBarWithNavController(navController, appBarConfiguration)
        bottomNavigationItem.setupWithNavController(navController)

    }
}