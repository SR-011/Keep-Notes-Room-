package com.practice.calendarevent.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.practice.calendarevent.R
import com.practice.calendarevent.data.model.Event
import com.practice.calendarevent.databinding.FragmentDetailBinding
import com.practice.calendarevent.viewmodel.EventViewModel

class DetailFragment : Fragment() {
    private val eventViewModel : EventViewModel by activityViewModels()
    private lateinit var binding: FragmentDetailBinding
    private lateinit var event: Event

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
       /* arguments?.let {
            event = it.getParcelable("key")!!
            Log.d("bundle", "onCreateView: $event")
        }*/
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_detail, container, false)
        binding.variable = eventViewModel
        setupObserver()
        return binding.root
    }

    private fun setupObserver() {
        eventViewModel.selectedEvent.observe(viewLifecycleOwner, {
            event = it
            binding.buDelete.setOnClickListener {
                eventViewModel.deleteEvent(event)
                binding.root.visibility = View.GONE
                Toast.makeText(context, "${event.title} has been deleted", Toast.LENGTH_SHORT).show()
                findNavController().navigate(R.id.eventFragment)
                Log.d("Sohel", "setupObserver: ${event.id}")
            }
            binding.buUpdate.setOnClickListener {
                val title = binding.title.text.toString()
                val description = binding.description.text.toString()
                val status = binding.status.text.toString()
                val timestamp = binding.timestamp.text.toString()
               /* val event = Event(id = event.id, title = title,description = description, status = status,timeStamp = timestamp)
                eventViewModel.updateEvent(event)*/
                Log.d("Sohel", "click")
                Log.d("Sohel", "setupObserverFor Update: ${event.id}")
            }
        })
    }
}