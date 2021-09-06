package com.coherentsolutions.by.max.sir.androidtrainingtasks.home

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.coherentsolutions.by.max.sir.androidtrainingtasks.MyApplication.Companion.INFO_TAG
import com.coherentsolutions.by.max.sir.androidtrainingtasks.R
import com.coherentsolutions.by.max.sir.androidtrainingtasks.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

    companion object {
        const val USER = "USER"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.i(INFO_TAG,"home activity created")

        super.onCreate(savedInstanceState)
        val binding =
            DataBindingUtil.setContentView<ActivityHomeBinding>(this, R.layout.activity_home)

        Log.i(INFO_TAG,"Home act inflated")
        val bottomNavigationItem = binding.bottomNavigation
        Log.i(INFO_TAG,"BOTTOM NAV ITEM INSTANTIATED")
        val navController = this.findNavController(R.id.myNavHostFragment)
        Log.i(INFO_TAG,"NAV CONTROLLER GOT")
        val appBarConfiguration = AppBarConfiguration(setOf(R.id.userFragment, R.id.petsFragment))
        setupActionBarWithNavController(navController, appBarConfiguration)
        Log.i(INFO_TAG,"NAV BAR CONFIGURED")
        bottomNavigationItem.setupWithNavController(navController)

    }
}