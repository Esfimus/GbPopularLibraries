package com.esfimus.popularlibraries.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.esfimus.popularlibraries.databinding.RepositoryRecyclerviewItemBinding
import com.esfimus.popularlibraries.mvp.presenter.repositories.RepositoryListPresenterInterface
import com.esfimus.popularlibraries.mvp.view.repositories.RepositoryItemView

class RepositoryRecyclerAdapter(private val presenter: RepositoryListPresenterInterface) :
RecyclerView.Adapter<RepositoryRecyclerAdapter.ViewHolder>(){

    inner class ViewHolder(private val ui: RepositoryRecyclerviewItemBinding) :
        RecyclerView.ViewHolder(ui.root), RepositoryItemView {
        override var pos = INVALID_INDEX

        override fun setRepositoryName(name: String) {
            ui.repositoryName.text = name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(RepositoryRecyclerviewItemBinding.inflate(
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