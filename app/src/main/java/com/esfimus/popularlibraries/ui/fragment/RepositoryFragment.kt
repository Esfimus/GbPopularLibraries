package com.esfimus.popularlibraries.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.esfimus.popularlibraries.App
import com.esfimus.popularlibraries.databinding.FragmentRepositoryBinding
import com.esfimus.popularlibraries.mvp.model.entity.GithubRepository
import com.esfimus.popularlibraries.mvp.presenter.RepositoryPresenter
import com.esfimus.popularlibraries.mvp.view.RepositoryView
import com.esfimus.popularlibraries.ui.activity.BackButtonListener
import com.github.terrakok.cicerone.Router
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import javax.inject.Inject

private const val REPOSITORY = "selected_repository"

class RepositoryFragment : MvpAppCompatFragment(), RepositoryView, BackButtonListener {

    private var _ui: FragmentRepositoryBinding? = null
    private val ui get() = _ui!!

    @Inject
    lateinit var router: Router

    @Suppress("Deprecation")
    val presenter: RepositoryPresenter by moxyPresenter {
        val repository = arguments?.getParcelable<GithubRepository>(REPOSITORY) as GithubRepository
        RepositoryPresenter(repository).apply {
            App.instance.repositorySubcomponent?.inject(this)
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(repository: GithubRepository) = RepositoryFragment().apply {
            arguments = Bundle().apply { putParcelable(REPOSITORY, repository) }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View {
        _ui = FragmentRepositoryBinding.inflate(inflater, container, false)
        return ui.root
    }

    override fun onDestroyView() {
        _ui = null
        super.onDestroyView()
    }

    override fun init() {}

    override fun setFullName(name: String) {
        ui.name.text = name
    }

    override fun setDescription(description: String) {
        ui.description.text = description
    }

    override fun setForksCount(forks: String) {
        ui.forksCount.text = forks
    }

    override fun setCreatedAt(createdAt: String) {
        ui.created.text = createdAt
    }

    override fun setUpdatedAt(updatedAt: String) {
        ui.updated.text = updatedAt
    }

    override fun setHomepage(homepage: String) {
        ui.homepage.text = homepage
    }

    override fun setLanguage(language: String) {
        ui.language.text = language
    }

    override fun backPressed() = presenter.backPressed()
}