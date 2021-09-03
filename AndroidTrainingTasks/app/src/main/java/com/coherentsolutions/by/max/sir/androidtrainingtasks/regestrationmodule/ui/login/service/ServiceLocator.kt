package com.coherentsolutions.by.max.sir.androidtrainingtasks.regestrationmodule.ui.login.service

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import com.coherentsolutions.by.max.sir.androidtrainingtasks.MyApplication.Companion.INFO_TAG
import com.coherentsolutions.by.max.sir.androidtrainingtasks.home.SharedPrefUserPersistance
import com.coherentsolutions.by.max.sir.androidtrainingtasks.home.UserPersistance
import kotlin.reflect.KClass

@SuppressLint("StaticFieldLeak")
object ServiceLocator {

    var context: Context? = null

    fun <T> getService(service: KClass<*>): T {
        return when (service) {
            RetrofitService::class -> PetstoreService() as T//.retrofit.create(service.java) as T
            else -> {
                throw Exception("wrong service $service")
            }
        }
    }

    fun <T> getPersistence(service: KClass<*>): T {
        Log.i(INFO_TAG, "service created")

        return when (service) {
            // PetPersistence::class -> DefaultPetPersistence() as T
            UserPersistance::class -> {
                run{
                    Log.i(INFO_TAG, "User persistence instantiated")
                }
                SharedPrefUserPersistance(context!!) as T
            }
            else -> {
                throw Exception("wrong persistence $service")
            }
        }
    }
}

class PetstoreService

class RetrofitService

inline fun <reified T> service(): T {
    Log.i(INFO_TAG, "service created")
    return ServiceLocator.getService(T::class)
}

inline fun <reified T> persistence(): T {
    Log.i(INFO_TAG, "persistence created")

    return ServiceLocator.getPersistence(T::class)
}
