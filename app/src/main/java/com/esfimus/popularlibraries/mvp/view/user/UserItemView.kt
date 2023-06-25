package com.esfimus.popularlibraries.mvp.view.user

import com.esfimus.popularlibraries.mvp.view.ItemView

interface UserItemView: ItemView {
    fun setLogin(text: String)

    fun loadAvatar(url: String)
}