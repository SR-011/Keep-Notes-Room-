package com.practice.calendarevent.data.repository

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import com.practice.calendarevent.data.db.AppDataBase
import com.practice.calendarevent.data.model.Event

class EventRepository(application: Application) {
    private val db = AppDataBase.getInstance(application)
    private val dao = db?.eventDao()

    fun getEvent(): LiveData<List<Event>>? {
        return dao?.getEvents()
    }

    suspend fun insertEvent(event: Event) {
        dao?.insertEvent(event)
        Log.d("Sohel", "insertEventToDatabase: $event")
    }
}