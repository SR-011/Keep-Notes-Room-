package com.practice.calendarevent.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.practice.calendarevent.data.model.Event
import com.practice.calendarevent.data.repository.EventRepository
import kotlinx.coroutines.launch

class EventViewModel() : ViewModel() {
    private val eventRepository = EventRepository()
    private var _eventList = MutableLiveData<List<Event>>()
    var eventList: LiveData<List<Event>>? = null

    init {
        Log.d("sohel", "view model initiated")
        getEvents()
    }
    fun insertEvents(event: Event) {
        viewModelScope.launch {
            eventRepository.insertEvent(event)
        }
    }
    private fun getEvents() {
        viewModelScope.launch {
            eventList = eventRepository.getEvent()
        }
    }
}