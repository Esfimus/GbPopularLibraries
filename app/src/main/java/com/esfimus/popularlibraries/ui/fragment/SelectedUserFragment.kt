package com.esfimus.popularlibraries.ui.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.esfimus.popularlibraries.App
import com.esfimus.popularlibraries.databinding.FragmentSelectedUserBinding
import com.esfimus.popularlibraries.mvp.model.api.repositories.ApiUserRepositories
import com.esfimus.popularlibraries.mvp.model.entity.GithubUser
import com.esfimus.popularlibraries.mvp.model.entity.room.RoomRepositoriesCache
import com.esfimus.popularlibraries.mvp.model.network.NetworkStatusInterface
import com.esfimus.popularlibraries.mvp.model.repo.repositories.RetrofitGithubRepositories
import com.esfimus.popularlibraries.mvp.presenter.repositories.RepositoryPresenter
import com.esfimus.popularlibraries.mvp.view.repositories.RepositoryView
import com.esfimus.popularlibraries.navigation.OpenRepository
import com.esfimus.popularlibraries.ui.activity.BackButtonListener
import com.esfimus.popularlibraries.ui.adapter.RepositoryRecyclerAdapter
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import javax.inject.Inject

private const val USER = "selected_user"

class SelectedUserFragment(val name: String) : MvpAppCompatFragment(), RepositoryView, BackButtonListener {

    private var _ui: FragmentSelectedUserBinding? = null
    private val ui get() = _ui!!
    private var user: GithubUser? = null
    private var adapter: RepositoryRecyclerAdapter? = null

    @Inject
    lateinit var router: Router
    @Inject
    lateinit var openRepository: OpenRepository
    @Inject
    lateinit var networkStatus : NetworkStatusInterface

    private val presenter: RepositoryPresenter by moxyPresenter {
        RepositoryPresenter(
            AndroidSchedulers.mainThread(),
            RetrofitGithubRepositories(
                ApiUserRepositories.api,
                networkStatus,
                RoomRepositoriesCache()
            ),
            router,
            openRepository,
            name
        )
    }

    companion object {
        @JvmStatic
        fun newInstance(user: GithubUser) = SelectedUserFragment(user.login!!).apply {
            arguments = Bundle().apply { putParcelable(USER, user) }
            App.instance.appComponent.inject(this)
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

    @Suppress("DEPRECATION")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            user = it.getParcelable(USER)
        }
    }

    override fun init() {
        with (ui) {
            login.text = user?.login ?: ""
            recyclerRepositories.layoutManager = LinearLayoutManager(context)
            adapter = RepositoryRecyclerAdapter(presenter.repositoryListPresenter)
            recyclerRepositories.adapter = adapter
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun updateList() {
        adapter?.notifyDataSetChanged()
    }

    override fun backPressed() = presenter.backPressed()

}