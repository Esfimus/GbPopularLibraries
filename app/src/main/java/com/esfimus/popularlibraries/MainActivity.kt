package com.esfimus.popularlibraries

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.esfimus.popularlibraries.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var ui: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ui = ActivityMainBinding.inflate(layoutInflater).also { setContentView(it.root) }
    }
}