package com.ludovicotech.githubviewer_android

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.annotation.IdRes
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.ludovicotech.githubviewer_android.model.User
import com.ludovicotech.githubviewer_android.provider.API_BASE_URL
import com.ludovicotech.githubviewer_android.provider.EndPoint
import com.ludovicotech.githubviewer_android.provider.NetworkProvider
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchActivity : AppCompatActivity() {

    private val txtSearch by lazy {
        findViewById<EditText>(R.id.searchText)
    }
    private val btnSearch by lazy {
        findViewById<Button>(R.id.searchButton)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        btnSearch.setOnClickListener() {
            Toast.makeText(this, "Search", Toast.LENGTH_SHORT).show()
            getData()
        }
    }
    fun getData() {
        val retrofitClient = NetworkProvider.getRetrofitInstance()

        val endPoint = retrofitClient.create(EndPoint::class.java)
        val callback = endPoint.getUsers()

        callback.enqueue(object : Callback<List<User>> {
            override fun onFailure(call: Call<List<User>>, t: Throwable) {
                Toast.makeText(baseContext, t.message, Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
                response.body()?.forEach {
                    println(it)
                }
            }
        })
    }
}
