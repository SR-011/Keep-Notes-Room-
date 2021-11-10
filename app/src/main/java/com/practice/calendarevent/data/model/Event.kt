package com.practice.calendarevent.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "event_table")
data class Event(
    @PrimaryKey(autoGenerate = true) val  id: Int,
    @ColumnInfo(name = "status") val status: String,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "timeStamp") val timeStamp: String
) {
    constructor() : this(
        id = 0,
        status = "",
        title = "",
        description = "",
        timeStamp = ""
    )
}