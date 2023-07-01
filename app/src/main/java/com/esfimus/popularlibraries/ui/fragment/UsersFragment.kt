package com.esfimus.popularlibraries.ui.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.esfimus.popularlibraries.App
import com.esfimus.popularlibraries.databinding.FragmentUsersBinding
import com.esfimus.popularlibraries.di.user.UserSubcomponent
import com.esfimus.popularlibraries.mvp.presenter.UsersPresenter
import com.esfimus.popularlibraries.mvp.view.UsersView
import com.esfimus.popularlibraries.ui.activity.BackButtonListener
import com.esfimus.popularlibraries.ui.adapter.UsersRecyclerAdapter
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class UsersFragment : MvpAppCompatFragment(), UsersView, BackButtonListener {

    private var _ui: FragmentUsersBinding? = null
    private val ui get() = _ui!!
    private var adapter: UsersRecyclerAdapter? = null
    private var userSubcomponent: UserSubcomponent? = null
    private val presenter: UsersPresenter by moxyPresenter {
        UsersPresenter().apply {
            userSubcomponent = App.instance.initUserSubcomponent()
            userSubcomponent?.inject(this)
        }
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
        with (ui) {
            recyclerUsers.layoutManager = LinearLayoutManager(context)
            adapter = UsersRecyclerAdapter(presenter.usersListPresenter).apply {
                userSubcomponent?.inject(this)
            }
            recyclerUsers.adapter = adapter
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun updateList() {
        adapter?.notifyDataSetChanged()
    }

    override fun backPressed() = presenter.backPressed()
}