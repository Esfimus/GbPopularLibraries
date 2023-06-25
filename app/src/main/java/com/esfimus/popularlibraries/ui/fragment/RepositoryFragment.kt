package com.esfimus.popularlibraries.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.esfimus.popularlibraries.databinding.FragmentRepositoryBinding
import com.esfimus.popularlibraries.mvp.model.entity.GithubRepository

private const val REPOSITORY = "selected_repository"

class RepositoryFragment : Fragment() {

    private var _ui: FragmentRepositoryBinding? = null
    private val ui get() = _ui!!
    private var repository: GithubRepository? = null

    companion object {
        @JvmStatic
        fun newInstance(repository: GithubRepository) = RepositoryFragment().apply {
            arguments = Bundle().apply { putParcelable(REPOSITORY, repository) }
        }
    }

    @Suppress("DEPRECATION")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            repository = it.getParcelable(REPOSITORY)
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        val repositoryName = "Name: ${repository?.fullName}"
        val repositoryDescription = "Description: ${repository?.description}"
        val repositoryCreated = "Created at: ${repository?.createdAt}"
        val repositoryUpdated = "Updated at: ${repository?.updatedAt}"
        val repositoryHomepage = "Homepage: ${repository?.homepage}"
        val repositoryLanguage = "Language: ${repository?.language}"
        val repositoryForks = "Forks count: ${repository?.forksCount}"

        with (ui) {
            name.text = repositoryName
            description.text = repositoryDescription
            created.text = repositoryCreated
            updated.text = repositoryUpdated
            homepage.text = repositoryHomepage
            language.text = repositoryLanguage
            forksCount.text = repositoryForks
        }
    }

}