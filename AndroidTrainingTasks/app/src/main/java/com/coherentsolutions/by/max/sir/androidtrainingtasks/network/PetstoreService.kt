package com.coherentsolutions.by.max.sir.androidtrainingtasks.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object PetstoreService {

    const val API_KEY = "hekko"

    const val SERVER_TAG = "pet-store"
    const val BASE_URL = "https://petstore.swagger.io/v2/"

    val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .baseUrl(BASE_URL)
            .build()
    }

}