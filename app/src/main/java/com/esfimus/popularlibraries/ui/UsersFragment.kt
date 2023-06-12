package com.esfimus.popularlibraries.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.esfimus.popularlibraries.App
import com.esfimus.popularlibraries.databinding.FragmentUsersBinding
import com.esfimus.popularlibraries.mvp.model.GithubUsersRepo
import com.esfimus.popularlibraries.mvp.presenter.UsersPresenter
import com.esfimus.popularlibraries.mvp.view.UsersView
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class UsersFragment : MvpAppCompatFragment(), UsersView, BackButtonListener {

    private var _ui: FragmentUsersBinding? = null
    private val ui get() = _ui!!
    var adapter: RecyclerAdapter? = null
    private val presenter: UsersPresenter by moxyPresenter {
        UsersPresenter(GithubUsersRepo(), App.instance.router)
    }

    companion object { fun newInstance() = UsersFragment() }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) =
        FragmentUsersBinding.inflate(inflater, container, false).also {
            _ui = it
        }.root

    override fun onDestroyView() {
        super.onDestroyView()
        _ui = null
    }

    override fun init() {
        ui.recyclerUsers.layoutManager = LinearLayoutManager(context)
        adapter = RecyclerAdapter(presenter.usersListPresenter)
        ui.recyclerUsers.adapter = adapter
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun updateList() {
        adapter?.notifyDataSetChanged()
    }

    override fun backPressed() = presenter.backPressed()
}