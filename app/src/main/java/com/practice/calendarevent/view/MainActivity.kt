package com.practice.calendarevent.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.practice.calendarevent.R
import com.practice.calendarevent.data.model.Event
import com.practice.calendarevent.databinding.ActivityMainBinding
import java.text.DateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var  customView: View
    private lateinit var materialAlertDialogBuilder: MaterialAlertDialogBuilder
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        materialAlertDialogBuilder = MaterialAlertDialogBuilder(this)
        binding.fab.setOnClickListener {
            customView = LayoutInflater.from(this)
                .inflate(R.layout.custom_layout, null, false)
            createDialog()
        }
    }
    private fun createDialog() {
        val title: TextView = customView.findViewById(R.id.title)
        val description: TextView = customView.findViewById(R.id.description)

        materialAlertDialogBuilder.setView(customView)
            .setMessage("Create new event?")
            .setTitle("Details")
            .setMessage("Enter your basic details")
            .setPositiveButton("Yes") { dialog, _ ->
                val positiveButtonStatus = "Accept"
                val titleText = title.text.toString()
                val descriptionText = description.text.toString()
                val timeStamp: String = DateFormat.getDateTimeInstance().format(Date())
                Log.d("Sohel", "createDialog: $positiveButtonStatus, $titleText, $descriptionText $timeStamp ")

            }
            .setNegativeButton("No") { dialog, _ ->
                val negativeButtonStatus = "Decline"
                val titleText = title.text.toString()
                val descriptionText = description.text.toString()
                val timeStamp: String = DateFormat.getDateTimeInstance().format(Date())
                Log.d("Sohel", "createDialog: $negativeButtonStatus, $titleText, $descriptionText $timeStamp ")
            }.show()
    }

}