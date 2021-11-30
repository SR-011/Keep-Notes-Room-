package com.practice.calendarevent.data.repository

import android.util.Log
import com.practice.calendarevent.data.db.AppDataBase
import com.practice.calendarevent.data.model.Event

class EventRepository() {
    private val db = AppDataBase.getInstance()
    private val dao = db?.getEventDao()

    fun getEvent(): List<Event>? {
        return dao?.getEvents()
    }

    suspend fun insertEvent(event: Event) {
        dao?.insertEvent(event)
        Log.d("Sohel", "insertEventToDatabase: $event")
    }

    suspend fun deleteEvent(event: Event) {
        dao?.deleteEvent(event)
    }

    suspend fun updateEvent(event: Event) {
        dao?.deleteEvent(event)
    }
}