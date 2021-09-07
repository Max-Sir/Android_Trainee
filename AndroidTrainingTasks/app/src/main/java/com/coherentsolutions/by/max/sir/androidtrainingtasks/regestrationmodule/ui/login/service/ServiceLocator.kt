package com.coherentsolutions.by.max.sir.androidtrainingtasks.regestrationmodule.ui.login.service

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import com.coherentsolutions.by.max.sir.androidtrainingtasks.MyApplication.Companion.INFO_TAG
import com.coherentsolutions.by.max.sir.androidtrainingtasks.home.DefaultPetPersistence
import com.coherentsolutions.by.max.sir.androidtrainingtasks.home.PetPersistence
import com.coherentsolutions.by.max.sir.androidtrainingtasks.home.SharedPrefUserPersistance
import com.coherentsolutions.by.max.sir.androidtrainingtasks.home.UserPersistance
import com.coherentsolutions.by.max.sir.androidtrainingtasks.network.PetstoreService
import com.coherentsolutions.by.max.sir.androidtrainingtasks.network.RetrofitService
import kotlin.reflect.KClass

@SuppressLint("StaticFieldLeak")
@Suppress("UNCHECKED_CAST")
object ServiceLocator {

    var context: Context? = null


    fun <T> getService(service: KClass<*>): T {
        return when (service) {
            RetrofitService::class -> PetstoreService.retrofit.create(service.java) as T
            else -> {
                throw Exception("wrong service $service")
            }
        }
    }

    fun <T> getPersistence(service: KClass<*>): T {
        Log.i(INFO_TAG, "GET PERSISTENCE CALL")

        return when (service) {
            //TODO("implement DefaultPetPersistence::class next steps, it's an template for the future")
            PetPersistence::class -> DefaultPetPersistence() as T
            UserPersistance::class -> SharedPrefUserPersistance(context!!) as T
            else -> {
                Log.i(INFO_TAG, "BAD PERSISTENCE - NOT CREATED")
                throw Exception("wrong persistence $service")
            }
        }
    }
}


inline fun <reified T> service(): T {
    Log.i(INFO_TAG, "SERVICE FUN")
    return ServiceLocator.getService(T::class)
}

inline fun <reified T> persistence(): T {
    Log.i(INFO_TAG, "PERSISTENCE FUN CALL")

    return ServiceLocator.getPersistence(T::class)
}
