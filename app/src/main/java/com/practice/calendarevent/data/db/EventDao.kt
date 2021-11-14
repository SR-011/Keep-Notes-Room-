package com.practice.calendarevent.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.practice.calendarevent.data.model.Event

@Dao
interface EventDao {
    @Insert
    suspend fun insertEvent(event: Event)

    @Query ("SELECT * FROM event_table ORDER BY id DESC")
    fun getEvents(): LiveData<List<Event>>
}