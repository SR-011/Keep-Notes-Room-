package com.practice.calendarevent.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "event_table")
data class Event(
    @PrimaryKey(autoGenerate = true) val  id: Int,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "description") val description: String
)