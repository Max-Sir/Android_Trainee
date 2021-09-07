package com.coherentsolutions.by.max.sir.androidtrainingtasks.network

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST

interface RetrofitService {
    @POST(value = "user")
    suspend fun createUser():Call<ServerResponse>
    // TODO('''@GET and @DELETE)

    @GET(value="user")
    suspend fun getUsers()

}