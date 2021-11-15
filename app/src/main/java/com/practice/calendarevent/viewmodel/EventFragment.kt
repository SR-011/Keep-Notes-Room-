package com.practice.calendarevent.viewmodel

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.practice.calendarevent.R
import com.practice.calendarevent.data.model.Event
import com.practice.calendarevent.databinding.FragmentEventBinding
import com.practice.calendarevent.view.EventAdapter
import java.text.DateFormat
import java.util.*

class EventFragment : Fragment(), EventAdapter.OnEventClickListener {

    private lateinit var customView: View
    private lateinit var materialAlertDialogBuilder: MaterialAlertDialogBuilder
    private val eventViewModel : EventViewModel by viewModels()
    private lateinit var eventAdapter: EventAdapter
    private lateinit var binding: FragmentEventBinding
    lateinit var eventList: ArrayList<Event>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_event, container, false)
        materialAlertDialogBuilder = MaterialAlertDialogBuilder(requireContext())
        eventList = ArrayList()
        binding.fab.setOnClickListener {
            customView = LayoutInflater.from(requireContext())
                .inflate(R.layout.custom_layout, null, false)
            createDialog()
        }
        setupObserver()
        setupUi()
        return binding.root
    }


    private fun setupObserver() {
        eventViewModel.eventList?.observe(viewLifecycleOwner, {
            Log.d("Sohel", "setupObserver: $it")
            eventList.clear()
            eventList.addAll(it)
            eventAdapter.notifyDataSetChanged()
        })
    }

    private fun setupUi() {
        eventAdapter = EventAdapter(this, eventList)
        val layoutManager = LinearLayoutManager(requireContext())
        binding.recycler.layoutManager = layoutManager
        binding.recycler.adapter = eventAdapter
    }

    private fun createDialog() {
        val title: TextView = customView.findViewById(R.id.title)
        val description: TextView = customView.findViewById(R.id.description)

        materialAlertDialogBuilder.setView(customView)
            .setMessage("Create new event?")
            .setPositiveButton("Yes") { _, _ ->
                val positiveButtonStatus = "Accepted"
                val titleText = title.text.toString()
                val descriptionText = description.text.toString()
                val timeStamp: String = DateFormat.getDateTimeInstance().format(Date())
                Log.d(
                    "Sohel",
                    "createDialog: $positiveButtonStatus, $titleText, $descriptionText $timeStamp "
                )
                val event = Event(
                    status = positiveButtonStatus,
                    title = titleText,
                    description = descriptionText,
                    timeStamp = timeStamp
                )
                eventViewModel.insertEvents(event).also {
                    //do action here
                    Log.d("Sohel", "createDialog: $event")
                }
            }
            .setNegativeButton("No") { _, _ ->
                val negativeButtonStatus = "Declined"
                val titleText = title.text.toString()
                val descriptionText = description.text.toString()
                val timeStamp: String = DateFormat.getDateTimeInstance().format(Date())
                Log.d(
                    "Sohel",
                    "createDialog: $negativeButtonStatus, $titleText, $descriptionText $timeStamp "
                )
                val event = Event(
                    status = negativeButtonStatus,
                    title = titleText,
                    description = descriptionText,
                    timeStamp = timeStamp
                )
                eventViewModel.insertEvents(event)

                    .also {
                        //do action here
                        Log.d("Sohel", "createDialog: $event")
                    }
            }.show()
    }

    override fun onEventClick(event: Event) {
        eventViewModel.selectEvent(event)
        findNavController().navigate(R.id.detailFragment)
        Log.d("Sohel", "onEventClick: ${event.title}")
    }
}