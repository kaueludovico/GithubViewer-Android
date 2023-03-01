package com.zallpy.githubviewer.network

import com.zallpy.githubviewer.model.Repository
import com.zallpy.githubviewer.model.User
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path


interface EndPointPath {
    @GET("/users/{userName}")
    fun getUsers(
        @Path("userName")
        userName: String
    ) : Call<User>

    @GET("/users/{userName}/repos")
    fun getRepos(
        @Path("userName")
        userName: String
    ) : Call<List<Repository>>
}