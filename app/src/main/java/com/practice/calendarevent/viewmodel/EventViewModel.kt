package com.practice.calendarevent.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.practice.calendarevent.data.model.Event
import com.practice.calendarevent.data.repository.EventRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EventViewModel() : ViewModel() {
    private val eventRepository = EventRepository()
    private var _eventList = MutableLiveData<List<Event>>()
    var eventList: LiveData<List<Event>>? = _eventList //null
    val selectedEvent = MutableLiveData<Event>()

    init {
        Log.d("sohel", "view model initiated")
        getEvents()
    }
    fun insertEvents(event: Event) {
        viewModelScope.launch {
            eventRepository.insertEvent(event)
            getEvents()
        }
    }
    private fun getEvents() {
        viewModelScope.launch(Dispatchers.IO) {
            _eventList.postValue(eventRepository.getEvent())
        }
    }

    fun selectEvent(event: Event) {
        selectedEvent.value = event
    }
}