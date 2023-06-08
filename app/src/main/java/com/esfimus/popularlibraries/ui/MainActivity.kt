package com.esfimus.popularlibraries.ui

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.esfimus.popularlibraries.databinding.ActivityMainBinding
import com.esfimus.popularlibraries.domain.Presenter

class MainActivity : AppCompatActivity() {

    private lateinit var ui: ActivityMainBinding
    private val presenter = Presenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ui = ActivityMainBinding.inflate(layoutInflater).also { setContentView(it.root) }
        initView()
    }

    private fun initView() {
        initButton(ui.buttonA)
        initButton(ui.buttonB)
        initButton(ui.buttonC)
    }

    private fun initButton(button: Button) {
        button.text = presenter.getValue(button.id).toString()
        button.setOnClickListener {
            updateValue(button)
        }
    }

    private fun updateValue(button: Button) {
        presenter.updateValue(button.id)
        button.text = presenter.getValue(button.id).toString()
    }
}