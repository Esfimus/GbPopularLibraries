package com.esfimus.popularlibraries.di.module

import com.esfimus.popularlibraries.App
import com.esfimus.popularlibraries.mvp.model.api.repositories.RepositorySourceInterface
import com.esfimus.popularlibraries.mvp.model.api.user.DataSourceInterface
import com.esfimus.popularlibraries.mvp.model.network.NetworkStatusInterface
import com.esfimus.popularlibraries.ui.network.AndroidNetworkStatus
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
class ApiModule {

    @Named("baseUrl")
    @Provides
    fun baseUrl(): String = "https://api.github.com/"

    @Singleton
    @Provides
    fun api(@Named("baseUrl") baseUrl: String, gson: Gson): DataSourceInterface =
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(DataSourceInterface::class.java)

    @Singleton
    @Provides
    fun apiRepositories(@Named("baseUrl") baseUrl: String, gson: Gson): RepositorySourceInterface =
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(RepositorySourceInterface::class.java)

    @Singleton
    @Provides
    fun gson() = GsonBuilder()
        .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
        .excludeFieldsWithoutExposeAnnotation()
        .create()

    @Singleton
    @Provides
    fun networkStatus(app: App): NetworkStatusInterface = AndroidNetworkStatus(app)
}