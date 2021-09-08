package com.coherentsolutions.by.max.sir.androidtrainingtasks.network

import com.coherentsolutions.by.max.sir.androidtrainingtasks.MyApplication.Companion.API_KEY
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
        @Header(value = "/autorization'")
        apiKey:String,
        @Body
        user: User
    ): Call<ServerResponse>

    @DELETE(value = "user")
    fun deleteUser(
        //@Header(value = "api_key'${API_KEY}'")
        @Path(value = "/{username}")
        username: String
    ): Call<ServerResponse>

    @GET(value = "user")
    fun getUser(
        //@Header(value = "api_key'${API_KEY}'")
        @Path(value = "/{username}")
        username: String
    ): Call<User>

}