package com.esfimus.popularlibraries.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.esfimus.popularlibraries.databinding.UserRecyclerviewItemBinding
import com.esfimus.popularlibraries.mvp.presenter.UserListPresenter
import com.esfimus.popularlibraries.mvp.view.ImageLoader
import com.esfimus.popularlibraries.mvp.view.UserItemView
import javax.inject.Inject

const val INVALID_INDEX = -1

class UsersRecyclerAdapter(private val presenter: UserListPresenter) :
    RecyclerView.Adapter<UsersRecyclerAdapter.ViewHolder>() {

    @Inject
    lateinit var imageLoader: ImageLoader<ImageView>

    inner class ViewHolder(private val ui: UserRecyclerviewItemBinding) :
        RecyclerView.ViewHolder(ui.root), UserItemView {
        override var pos = INVALID_INDEX

        override fun setLogin(text: String) {
            ui.login.text = text
        }

        override fun loadAvatar(url: String) {
            imageLoader.loadImage(url, ui.avatar)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(UserRecyclerviewItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false)).apply {
            itemView.setOnClickListener { presenter.itemClickListener?.invoke(this) }
        }

    override fun getItemCount() = presenter.getCount()

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        presenter.bindView(holder.apply {
            pos = position
        })
    }
}