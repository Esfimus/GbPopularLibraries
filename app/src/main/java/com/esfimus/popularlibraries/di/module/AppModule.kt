package com.esfimus.popularlibraries.di.module

import com.esfimus.popularlibraries.App
import dagger.Module
import dagger.Provides
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Scheduler

@Module
class AppModule(val app: App) {

    @Provides
    fun app(): App = app

    @Provides
    fun mainThreadScheduler(): Scheduler = AndroidSchedulers.mainThread()
}