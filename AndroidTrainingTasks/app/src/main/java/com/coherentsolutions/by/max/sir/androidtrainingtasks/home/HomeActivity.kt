package com.coherentsolutions.by.max.sir.androidtrainingtasks.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.coherentsolutions.by.max.sir.androidtrainingtasks.MyApplication.Companion.INFO_TAG
import com.coherentsolutions.by.max.sir.androidtrainingtasks.R
import com.coherentsolutions.by.max.sir.androidtrainingtasks.databinding.ActivityHomeBinding
import com.coherentsolutions.by.max.sir.androidtrainingtasks.regestrationmodule.ui.login.LoginActivity

class HomeActivity : AppCompatActivity() {

    companion object {
        const val USER = "USER"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.i(INFO_TAG, "home activity created")

        super.onCreate(savedInstanceState)
        val binding =
            DataBindingUtil.setContentView<ActivityHomeBinding>(this, R.layout.activity_home)

        Log.i(INFO_TAG, "Home act inflated")
        val bottomNavigationItem = binding.bottomNavigation
        Log.i(INFO_TAG, "BOTTOM NAV ITEM INSTANTIATED")
        val navController = this.findNavController(R.id.myNavHostFragment)
        Log.i(INFO_TAG, "NAV CONTROLLER GOT")
        val appBarConfiguration = AppBarConfiguration(setOf(R.id.userFragment, R.id.petsFragment))
        setupActionBarWithNavController(navController, appBarConfiguration)
        Log.i(INFO_TAG, "NAV BAR CONFIGURED")
        bottomNavigationItem.setupWithNavController(navController)

    }

    override fun onBackPressed() {
        AlertDialog.Builder(this)
            .setTitle("Logout?")
            .setMessage("Are you sure you wanna logout?")
            .setPositiveButton(getText(R.string.yes)) { dialog, _ ->
                startActivity(Intent(this, LoginActivity::class.java))
                dialog.cancel()
            }
            .setNegativeButton(getText(R.string.no)) { dialog, _ ->
                dialog.cancel()
            }
            .create()
            .show()
    }


}