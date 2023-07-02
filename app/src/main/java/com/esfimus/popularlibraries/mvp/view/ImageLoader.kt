package com.esfimus.popularlibraries.mvp.view

interface ImageLoader<T> {
    fun loadImage(url: String, container: T)
}