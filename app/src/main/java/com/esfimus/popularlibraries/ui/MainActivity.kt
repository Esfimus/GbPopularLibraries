package com.esfimus.popularlibraries.ui

import android.os.Bundle
import android.widget.Button
import com.esfimus.popularlibraries.databinding.ActivityMainBinding
import com.esfimus.popularlibraries.domain.Presenter
import com.esfimus.popularlibraries.domain.UpdateView
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter

class MainActivity : MvpAppCompatActivity(), UpdateView {

    private lateinit var ui: ActivityMainBinding
    private val presenter by moxyPresenter { Presenter() }

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
        updateCounter(button)
        button.setOnClickListener {
            presenter.increaseValue(button.id)
            updateCounter(button)
        }
    }

    private fun updateCounter(button: Button) {
        presenter.getValue(button.id, object : UpdateView {
            override fun setButtonText(count: String) {
                button.text = count
            }
        })
    }

    override fun setButtonText(count: String) { }

}