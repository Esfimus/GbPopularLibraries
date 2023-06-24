package com.esfimus.popularlibraries.ui

import android.os.Bundle
import android.view.View
import com.esfimus.popularlibraries.databinding.FragmentSelectedUserBinding
import com.esfimus.popularlibraries.mvp.model.entity.GithubUser

private const val USER = "selected_user"

class SelectedUserFragment : ViewBindingFragment<FragmentSelectedUserBinding>(
    FragmentSelectedUserBinding::inflate
) {

    private var user: GithubUser? = null

    companion object {
        @JvmStatic
        fun newInstance(user: GithubUser) = SelectedUserFragment().apply {
            arguments = Bundle().apply { putParcelable(USER, user) }
        }
    }

    @Suppress("DEPRECATION")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            user = it.getParcelable(USER)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        ui.login.text = user?.login ?: ""
    }
}