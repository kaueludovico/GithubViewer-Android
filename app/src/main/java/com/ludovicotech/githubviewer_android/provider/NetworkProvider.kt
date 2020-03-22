package com.ludovicotech.githubviewer_android.provider

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

var API_BASE_URL = "https://api.github.com"

class NetworkProvider {
    companion object {
        fun getRetrofitInstance() : Retrofit {
            return  Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
    }
}