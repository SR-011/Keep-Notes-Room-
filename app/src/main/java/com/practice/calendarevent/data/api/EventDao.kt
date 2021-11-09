package com.practice.calendarevent.data.api

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.practice.calendarevent.data.model.Event

@Dao
interface EventDao {
    @Insert
    suspend fun insertEvent(event: Event)
    @Query ("SELECT * FROM event_table")
    suspend fun loadEvents(): List<Event>
}