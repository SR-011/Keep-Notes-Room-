package com.practice.calendarevent.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.practice.calendarevent.data.model.Event
import com.practice.calendarevent.data.repository.EventRepository
import kotlinx.coroutines.launch

class EventViewModel(private val eventRepository: EventRepository): ViewModel() {
    private val _eventList = MutableLiveData<List<Event>>()
    val eventList: LiveData<List<Event>> = _eventList

    fun setEvents(){
        viewModelScope.launch {
            val event = eventRepository.insertEvent(event = Event())
        }
    }
    fun getEvents() {
        viewModelScope.launch {
            val event = eventRepository.loadEvent()
        }
    }
}