package com.practice.calendarevent.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.CalendarView
import android.widget.CalendarView.OnDateChangeListener
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import com.practice.calendarevent.R
import com.practice.calendarevent.data.model.Event
import com.practice.calendarevent.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        /*binding.calendarView.setOnDateChangeListener(object: OnDateChangeListener{
            override fun onSelectedDayChange(p0: CalendarView, p1: Int, p2: Int, p3: Int) {
                createDialog()
                Toast.makeText(this@MainActivity, "Today: $p1/${p2+1}/$p3", Toast.LENGTH_SHORT).show()
            }
        })*/

        binding.fab.setOnClickListener {
            createDialog()
        }
    }
    private fun createDialog() {
        val alertDialog = AlertDialog.Builder(this)

        alertDialog.apply {
            //setTitle("Create Event")
            setMessage("Create new event?")
            alertDialog.setView(layoutInflater.inflate(R.layout.custom_layout, null))
            setPositiveButton("Yes") { _, _ ->
                val positiveButtonStatus = "yes"
                val title: TextView = findViewById(R.id.title)
                val description: TextView = findViewById(R.id.description)
                val titleText = title.text
                val descriptionText = description.text
                Log.d("TAG", "createDialog: $positiveButtonStatus, $titleText, ${title.id}, $descriptionText, ${description.id} ")
            }
            setNegativeButton("No") {_, _ ->
                val negativeButtonStatus = "No"
            }
        }.create().show()

    }
}