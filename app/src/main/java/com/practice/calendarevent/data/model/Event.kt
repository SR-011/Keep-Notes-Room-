package com.practice.calendarevent.data.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "event_table")
data class Event(
    @PrimaryKey(autoGenerate = true) val  id: Int = 0,
    @ColumnInfo(name = "status") val status: String,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "timeStamp") val timeStamp: String
): Parcelable