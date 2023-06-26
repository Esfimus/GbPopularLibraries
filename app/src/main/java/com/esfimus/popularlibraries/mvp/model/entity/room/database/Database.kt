package com.esfimus.popularlibraries.mvp.model.entity.room.database

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase

const val DB_EXCEPTION = "Database has not been created."

@androidx.room.Database(entities = [RoomGithubUser::class, RoomGithubRepository::class], version = 1)
abstract class Database : RoomDatabase() {
    abstract val userDao: UserDao
    abstract val repositoryDao: RepositoryDao

    companion object {
        private const val DATABASE = "database.db"
        private var instance: Database? = null
        fun getInstance() = instance ?: throw RuntimeException(DB_EXCEPTION)

        fun create(context: Context?) {
            if (instance == null) {
                instance = Room.databaseBuilder(context!!, Database::class.java, DATABASE).build()
            }
        }
    }
}