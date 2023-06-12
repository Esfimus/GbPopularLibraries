package com.esfimus.popularlibraries.ui

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.esfimus.popularlibraries.databinding.ActivityMainBinding
import com.esfimus.popularlibraries.mvp.model.GithubUsersRepo
import com.esfimus.popularlibraries.mvp.presenter.MainPresenter
import com.esfimus.popularlibraries.mvp.view.MainView
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter

class MainActivity : MvpAppCompatActivity(), MainView {

    private lateinit var ui: ActivityMainBinding
    private val presenter by moxyPresenter { MainPresenter(GithubUsersRepo()) }
    private var adapter: RecyclerAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ui = ActivityMainBinding.inflate(layoutInflater).also { setContentView(it.root) }
    }

    override fun init() {
        ui.recyclerUsers.layoutManager = LinearLayoutManager(this)
        adapter = RecyclerAdapter(presenter.usersListPresenter)
        ui.recyclerUsers.adapter = adapter
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun updateList() {
        adapter?.notifyDataSetChanged()
    }
}