package com.practice.calendarevent.data.db

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.practice.calendarevent.MyApplication
import com.practice.calendarevent.data.model.Event

@Database(entities = [Event::class], version = 1)
abstract class AppDataBase : RoomDatabase() {
    abstract fun eventDao(): EventDao

    companion object {
        @Volatile
        private var appDatabase: AppDataBase? = null
        fun getInstance(): AppDataBase? {
            synchronized(this) {
                if (appDatabase == null) {
                    appDatabase =
                        Room.databaseBuilder(
                            MyApplication.context,
                            AppDataBase::class.java,
                            "Event_database"
                        )
                            .fallbackToDestructiveMigration()
                            .build()
                }
                return appDatabase
            }
        }

    }
}