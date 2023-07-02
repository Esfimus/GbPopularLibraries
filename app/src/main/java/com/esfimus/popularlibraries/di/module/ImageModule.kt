package com.esfimus.popularlibraries.di.module

import android.widget.ImageView
import com.esfimus.popularlibraries.mvp.view.ImageLoader
import com.esfimus.popularlibraries.ui.image.GlideImageLoader
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ImageModule {
    @Singleton
    @Provides
    fun imageLoader(): ImageLoader<ImageView> = GlideImageLoader()
}