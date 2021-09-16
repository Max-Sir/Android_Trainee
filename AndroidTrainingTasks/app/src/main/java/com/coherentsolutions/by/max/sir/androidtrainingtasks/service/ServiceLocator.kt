package com.coherentsolutions.by.max.sir.androidtrainingtasks.service

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import com.coherentsolutions.by.max.sir.androidtrainingtasks.MyApplication.Companion.INFO_TAG
import com.coherentsolutions.by.max.sir.androidtrainingtasks.network.PetstoreService
import com.coherentsolutions.by.max.sir.androidtrainingtasks.network.RetrofitService
import com.coherentsolutions.by.max.sir.androidtrainingtasks.persistence.*
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
            PetstorePersistence::class -> SharedPrefPetstorePersistence(context!!) as T
            UserPersistence::class -> DefaultUserPersistence(context!!) as T
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
