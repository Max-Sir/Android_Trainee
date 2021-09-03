package com.coherentsolutions.by.max.sir.androidtrainingtasks.regestrationmodule.ui.login.service

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.coherentsolutions.by.max.sir.androidtrainingtasks.MyApplication
import com.coherentsolutions.by.max.sir.androidtrainingtasks.home.SharedPrefUserPersistance
import com.coherentsolutions.by.max.sir.androidtrainingtasks.home.UserPersistance
import kotlin.reflect.KClass

@SuppressLint("StaticFieldLeak")
object ServiceLocator {

    var context:Context?=null

    fun <T> getService(service: KClass<*>): T {
        return when (service) {
            RetrofitService::class -> PetstoreService() as T//.retrofit.create(service.java) as T
            else -> {
                throw Exception("wrong service $service")
            }
        }
    }

    fun <T> getPersistence(service: KClass<*>): T {
        return when (service) {
           // PetPersistence::class -> DefaultPetPersistence() as T
            UserPersistance::class -> SharedPrefUserPersistance(context!!) as T
            else -> {
                throw Exception("wrong persistence $service")
            }
        }
    }
}

class PetstoreService {

}

class RetrofitService {

}

inline fun <reified T> service(): T {
    return ServiceLocator.getService(T::class)
}

inline fun <reified T> persistence(): T {
    return ServiceLocator.getPersistence(T::class)
}
