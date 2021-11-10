package com.practice.calendarevent.data.repository

import com.practice.calendarevent.data.api.EventDao
import com.practice.calendarevent.data.model.Event

class EventRepository(private val dao: EventDao) {
    val events = dao.loadEvents()
    suspend fun insertEvent(event: Event) {
        dao.insertEvent(event)
    }
}