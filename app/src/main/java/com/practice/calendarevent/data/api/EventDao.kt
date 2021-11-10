package com.practice.calendarevent.data.api

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.practice.calendarevent.data.model.Event

@Dao
interface EventDao {
    @Insert
    suspend fun insertEvent(event: Event)
    @Query ("SELECT * FROM event_table")
    //suspend fun loadEvents(): LiveData<List<Event>>
    fun getEvents(): LiveData<List<Event>>
}