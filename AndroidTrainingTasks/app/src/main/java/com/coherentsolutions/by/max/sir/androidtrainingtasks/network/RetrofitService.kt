package com.coherentsolutions.by.max.sir.androidtrainingtasks.network

import com.coherentsolutions.by.max.sir.androidtrainingtasks.home.entities.User
import retrofit2.Call
import retrofit2.http.*

interface RetrofitService {
    @POST(value = "user")
    fun createUser(@Body user: User): Call<ServerResponse>

    @DELETE(value = "user")
    fun deleteUser(@Path(value = "/{username}") username: String):Call<ServerResponse>

    @GET(value = "user")
    fun getUser(@Path(value = "/{username}") username:String): Call<User>

}