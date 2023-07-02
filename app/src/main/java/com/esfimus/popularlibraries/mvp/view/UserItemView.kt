package com.esfimus.popularlibraries.mvp.view

interface UserItemView: ItemView {
    fun setLogin(text: String)

    fun loadAvatar(url: String)
}