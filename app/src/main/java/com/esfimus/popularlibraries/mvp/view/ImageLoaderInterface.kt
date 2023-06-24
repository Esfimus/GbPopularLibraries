package com.esfimus.popularlibraries.mvp.view

interface ImageLoaderInterface<T> {
    fun loadImage(url: String, container: T)
}