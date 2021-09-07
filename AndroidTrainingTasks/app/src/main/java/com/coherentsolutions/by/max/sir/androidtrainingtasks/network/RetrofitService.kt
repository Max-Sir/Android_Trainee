package com.coherentsolutions.by.max.sir.androidtrainingtasks.network

import com.coherentsolutions.by.max.sir.androidtrainingtasks.home.entities.User
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface RetrofitService {
    @POST(value = "user")
    suspend fun createUser(@Body user: User):Call<ServerResponse>

    // TODO(@DELETE)

    @GET(value="user")
    suspend fun getUsers():Call<ServerResponse>

}