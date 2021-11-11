package com.practice.calendarevent.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.practice.calendarevent.R
import com.practice.calendarevent.data.model.Event
import com.practice.calendarevent.databinding.ActivityMainBinding
import com.practice.calendarevent.viewmodel.EventViewModel
import java.text.DateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var customView: View
    private lateinit var materialAlertDialogBuilder: MaterialAlertDialogBuilder
    private lateinit var eventViewModel: EventViewModel
    private lateinit var eventAdapter: EventAdapter
    private var eventList = listOf<Event>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        materialAlertDialogBuilder = MaterialAlertDialogBuilder(this)
        eventViewModel = EventViewModel(application)

        binding.fab.setOnClickListener {
            customView = LayoutInflater.from(this)
                .inflate(R.layout.custom_layout, null, false)
            createDialog()
        }
        setupObserver()
        setupUi()
    }

    private fun setupObserver() {
        eventViewModel.eventList?.observe(this,{
            eventList = it
            Log.d("Sohel", "setupObserver: $eventList")
            eventAdapter.addEvent(eventList)
        })
    }

    private fun setupUi(){
        eventAdapter = EventAdapter()
        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        binding.recycler.layoutManager = layoutManager
        binding.recycler.adapter = eventAdapter
    }

    private fun createDialog() {
        val title: TextView = customView.findViewById(R.id.title)
        val description: TextView = customView.findViewById(R.id.description)

        materialAlertDialogBuilder.setView(customView)
            .setMessage("Create new event?")
            .setPositiveButton("Yes") { _, _ ->
                val positiveButtonStatus = "Accept"
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
                val negativeButtonStatus = "Decline"
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

}