package com.coherentsolutions.by.max.sir.androidtrainingtasks

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import com.coherentsolutions.by.max.sir.androidtrainingtasks.regestrationmodule.ui.login.service.ServiceLocator
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class MyApplication : Application() {

    companion object {
        const val INFO_TAG = "loginfo"
        const val BASE_URL = "https://petstore.swagger.io/v2/"
    }


    override fun onCreate() {
        Log.i(INFO_TAG, "created application")
        super.onCreate()
        ServiceLocator.context = applicationContext
    }
}