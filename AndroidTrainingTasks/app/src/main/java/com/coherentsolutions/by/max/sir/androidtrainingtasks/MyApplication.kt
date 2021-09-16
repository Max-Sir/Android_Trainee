package com.coherentsolutions.by.max.sir.androidtrainingtasks

import android.app.Application
import android.util.Log
import com.coherentsolutions.by.max.sir.androidtrainingtasks.database.UserDao
import com.coherentsolutions.by.max.sir.androidtrainingtasks.database.UserDatabase
import com.coherentsolutions.by.max.sir.androidtrainingtasks.service.ServiceLocator
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job

class MyApplication : Application() {

    companion object {
        const val INFO_TAG = "MyApplication"

        val applicationJob by lazy {
            Job()
        }
        val uiScope by lazy {
            CoroutineScope(Dispatchers.Main + applicationJob)
        }
    }


    override fun onCreate() {
        Log.i(INFO_TAG, "created application")
        super.onCreate()
        ServiceLocator.context = applicationContext
    }

    override fun onTerminate() {
        super.onTerminate()
        applicationJob.cancel()
    }
}