package com.esfimus.popularlibraries.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.esfimus.popularlibraries.databinding.RepositoryRecyclerviewItemBinding
import com.esfimus.popularlibraries.mvp.presenter.RepositoryListPresenter
import com.esfimus.popularlibraries.mvp.view.RepositoryItemView

class RepositoriesRecyclerAdapter(private val presenter: RepositoryListPresenter) :
    RecyclerView.Adapter<RepositoriesRecyclerAdapter.ViewHolder>(){

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

    override fun onBindViewHolder(holder: RepositoriesRecyclerAdapter.ViewHolder, position: Int) {
        presenter.bindView(holder.apply {
            pos = position
        })
    }
}