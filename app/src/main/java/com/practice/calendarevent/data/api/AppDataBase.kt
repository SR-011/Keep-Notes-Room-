package com.practice.calendarevent.data.api

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.practice.calendarevent.data.model.Event

@Database(entities = [Event::class], version = 1)
abstract class AppDataBase: RoomDatabase() {
    abstract fun eventDao(): EventDao
    private var appDatabase: AppDataBase? = null
    fun getInstance(context: Context): AppDataBase? {
        if (appDatabase == null) {
            appDatabase =
                Room.databaseBuilder(
                    context.getApplicationContext(),
                    AppDataBase::class.java,
                    "YourDatabaseName.db"
                )
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build()
        }
        return appDatabase
    }
}