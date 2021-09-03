package com.coherentsolutions.by.max.sir.androidtrainingtasks

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.coherentsolutions.by.max.sir.androidtrainingtasks.regestrationmodule.ui.login.service.ServiceLocator

class MyApplication:Application() {

    override fun onCreate() {
        super.onCreate()
        ServiceLocator.context=applicationContext
    }
}