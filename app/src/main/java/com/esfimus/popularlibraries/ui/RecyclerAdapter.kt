package com.esfimus.popularlibraries.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.esfimus.popularlibraries.databinding.RecyclerviewItemBinding
import com.esfimus.popularlibraries.mvp.presenter.UserListPresenterInterface
import com.esfimus.popularlibraries.mvp.view.UserItemView

const val INVALID_INDEX = -1

class RecyclerAdapter(private val presenter: UserListPresenterInterface) :
RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

    inner class ViewHolder(private val ui: RecyclerviewItemBinding) :
        RecyclerView.ViewHolder(ui.root), UserItemView {
        override var pos = INVALID_INDEX

        override fun setLogin(text: String) {
            ui.login.text = text
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(RecyclerviewItemBinding.inflate(
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