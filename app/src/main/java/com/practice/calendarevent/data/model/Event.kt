package com.practice.calendarevent.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "event_table")
data class Event(
    @PrimaryKey(autoGenerate = true) val  id: Int,
    @ColumnInfo(name = "t") val title: String,
    @ColumnInfo(name = "bv") val description: String
)