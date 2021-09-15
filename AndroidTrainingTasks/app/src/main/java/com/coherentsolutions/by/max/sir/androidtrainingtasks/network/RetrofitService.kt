package com.coherentsolutions.by.max.sir.androidtrainingtasks.network

import com.coherentsolutions.by.max.sir.androidtrainingtasks.home.entities.User
import retrofit2.Call
import retrofit2.http.*

interface RetrofitService {
    @Headers(
        "Accept: application/json",
        "Content-Type: application/json",
        "Platform: android")
    @POST(value = "user")
    fun createUser(
        @Header(value = "/authorization")
        apiKey:String,
        @Body
        user: User
    ): Call<ServerStatusResponse>


    @Headers(
        "Accept: application/json",
        "Content-Type: application/json",
        "Platform: android")
    @DELETE(value = "user/{username}")
    fun deleteUser(
        @Header(value = "/authorization")
        apiKey:String,
        @Path(value = "username")
        username: String
    ): Call<ServerStatusResponse>

    @Headers(
        "Accept: application/json",
        "Content-Type: application/json",
        "Platform: android")
    @GET(value = "user/{username}")
    fun getUser(
        @Header(value = "/authorization")
        apiKey:String,
        @Path(value = "username")
        username: String
    ): Call<User>

}