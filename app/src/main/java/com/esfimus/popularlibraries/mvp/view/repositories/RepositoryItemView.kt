package com.esfimus.popularlibraries.mvp.view.repositories

import com.esfimus.popularlibraries.mvp.view.ItemView

interface RepositoryItemView : ItemView {
    fun setRepositoryName(name: String)
}