package com.zallpy.githubviewer.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Repository(
    @SerializedName("name")
    var title: String,
    @SerializedName("language")
    var language: String,
    @SerializedName("html_url")
    var htmlUrl: String
) : Serializable
