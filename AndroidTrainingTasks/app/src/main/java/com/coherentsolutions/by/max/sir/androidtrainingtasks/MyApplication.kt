package com.coherentsolutions.by.max.sir.androidtrainingtasks

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import com.coherentsolutions.by.max.sir.androidtrainingtasks.regestrationmodule.ui.login.service.ServiceLocator

class MyApplication:Application() {

    companion object {
        const val INFO_TAG="loginfo"
    }

    override fun onCreate() {
        Log.i(INFO_TAG,"created application")
        super.onCreate()
        ServiceLocator.context=applicationContext
    }
}