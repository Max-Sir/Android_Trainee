package com.coherentsolutions.by.max.sir.androidtrainingtasks

import android.app.Application
import android.util.Log
import com.coherentsolutions.by.max.sir.androidtrainingtasks.regestrationmodule.ui.login.service.ServiceLocator
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job

class MyApplication : Application() {

    companion object {
        const val INFO_TAG = "logInfo"
        const val SERVER_TAG = "server"

        val applicationJob by lazy {
            Job()
        }
        val uiScope by lazy {
            CoroutineScope(Dispatchers.Main+applicationJob)
        }
    }


    override fun onCreate() {
        Log.i(INFO_TAG, "created application")
        super.onCreate()
        ServiceLocator.context = applicationContext
    }
}