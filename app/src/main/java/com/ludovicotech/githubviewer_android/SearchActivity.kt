package com.ludovicotech.githubviewer_android

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast

class SearchActivity : AppCompatActivity() {

    private val txtSearch by lazy {
        findViewById<EditText>(R.id.searchText)
    }
    private val btnSearch by lazy {
        findViewById<EditText>(R.id.searchButton)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        
        btnSearch.setOnClickListener() {
            Toast.makeText(this, "Search", Toast.LENGTH_SHORT).show()
        }
    }
}
