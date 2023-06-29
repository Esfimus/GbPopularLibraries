package com.esfimus.popularlibraries.di.module

import android.widget.ImageView
import com.esfimus.popularlibraries.mvp.view.ImageLoaderInterface
import com.esfimus.popularlibraries.ui.image.GlideImageLoader
import dagger.Module
import dagger.Provides

@Module
class ImageModule {
    @Provides
    fun imageLoader(): ImageLoaderInterface<ImageView> = GlideImageLoader()
}