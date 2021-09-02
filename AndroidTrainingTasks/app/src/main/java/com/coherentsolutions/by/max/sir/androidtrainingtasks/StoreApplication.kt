package com.coherentsolutions.by.max.sir.androidtrainingtasks

import android.app.Application
import android.content.SharedPreferences

object StoreApplication:Application() {
    val preferences:SharedPreferences by lazy{
        getSharedPreferences("USER", MODE_PRIVATE)
    }
}