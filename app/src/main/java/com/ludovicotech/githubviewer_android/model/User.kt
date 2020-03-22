package com.ludovicotech.githubviewer_android.model

import com.google.gson.annotations.SerializedName

data class User (
    @SerializedName("name")
    var name: String,
    @SerializedName("avatar_url")
    var avatarUrl: String,
    @SerializedName("repos_url")
    var reposUrl: String
)