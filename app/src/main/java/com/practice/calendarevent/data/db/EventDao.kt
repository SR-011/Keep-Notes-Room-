package com.practice.calendarevent.data.db

import androidx.room.*
import com.practice.calendarevent.data.model.Event

@Dao
interface EventDao {
    @Insert
    suspend fun insertEvent(event: Event)

    @Delete
    suspend fun deleteEvent(event: Event)

    @Update
    suspend fun updateEvent(event: Event)

    @Query ("SELECT * FROM event_table ORDER BY id DESC")
    fun getEvents(): List<Event>
}