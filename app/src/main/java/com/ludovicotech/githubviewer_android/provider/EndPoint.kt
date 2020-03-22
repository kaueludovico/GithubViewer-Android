package com.ludovicotech.githubviewer_android.provider

import com.ludovicotech.githubviewer_android.model.User
import retrofit2.Call
import retrofit2.http.GET

interface EndPoint {
    @GET("/users")
    fun getUsers() : Call<List<User>>
}