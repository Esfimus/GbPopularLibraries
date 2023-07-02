package com.esfimus.popularlibraries.ui.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.esfimus.popularlibraries.App
import com.esfimus.popularlibraries.databinding.FragmentSelectedUserBinding
import com.esfimus.popularlibraries.mvp.model.entity.GithubUser
import com.esfimus.popularlibraries.mvp.presenter.SelectedUserPresenter
import com.esfimus.popularlibraries.mvp.view.UserView
import com.esfimus.popularlibraries.ui.activity.BackButtonListener
import com.esfimus.popularlibraries.ui.adapter.RepositoriesRecyclerAdapter
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

private const val USER = "selected_user"

class SelectedUserFragment : MvpAppCompatFragment(), UserView, BackButtonListener {

    private var _ui: FragmentSelectedUserBinding? = null
    private val ui get() = _ui!!
    private var adapter: RepositoriesRecyclerAdapter? = null

    @Suppress("Deprecation")
    private val presenter: SelectedUserPresenter by moxyPresenter {
        val user = arguments?.getParcelable<GithubUser>(USER) as GithubUser
        SelectedUserPresenter(user).apply {
            App.instance.initRepositorySubcomponent()?.inject(this)
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(user: GithubUser) = SelectedUserFragment().apply {
            arguments = Bundle().apply { putParcelable(USER, user) }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) =
        FragmentSelectedUserBinding.inflate(inflater, container, false).also {
            _ui = it
        }.root

    override fun onDestroyView() {
        super.onDestroyView()
        _ui = null
    }

    override fun init() {
        with (ui) {
            recyclerRepositories.layoutManager = LinearLayoutManager(context)
            adapter = RepositoriesRecyclerAdapter(presenter.repositoriesListPresenter)
            recyclerRepositories.adapter = adapter
        }
    }

    override fun setLogin(login: String) {
        ui.login.text = login
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun updateList() {
        adapter?.notifyDataSetChanged()
    }

    override fun backPressed() = presenter.backPressed()

}