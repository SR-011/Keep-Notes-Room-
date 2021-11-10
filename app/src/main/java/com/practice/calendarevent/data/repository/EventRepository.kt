package com.practice.calendarevent.data.repository

import androidx.lifecycle.LiveData
import com.practice.calendarevent.data.api.EventDao
import com.practice.calendarevent.data.model.Event

class EventRepository(private val dao: EventDao) {
    fun getEvent(): LiveData<List<Event>> {
        return dao.getEvents()
    }
    suspend fun insertEvent(event: Event) {
        dao.insertEvent(event)
    }
}