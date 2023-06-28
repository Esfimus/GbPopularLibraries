package com.esfimus.popularlibraries.di.module

import androidx.room.Room
import com.esfimus.popularlibraries.App
import com.esfimus.popularlibraries.mvp.model.entity.room.RepositoriesCacheInterface
import com.esfimus.popularlibraries.mvp.model.entity.room.RoomRepositoriesCache
import com.esfimus.popularlibraries.mvp.model.entity.room.RoomUserCache
import com.esfimus.popularlibraries.mvp.model.entity.room.UserCacheInterface
import com.esfimus.popularlibraries.mvp.model.entity.room.database.Database
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class CacheModule {

    @Singleton
    @Provides
    fun database(app: App): Database = Room.databaseBuilder(app, Database::class.java, Database.DATABASE)
        .build()

    @Singleton
    @Provides
    fun usersCache(): UserCacheInterface = RoomUserCache()

    @Singleton
    @Provides
    fun repositoriesCache(): RepositoriesCacheInterface = RoomRepositoriesCache()
}