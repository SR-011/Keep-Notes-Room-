package com.practice.calendarevent.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
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
        setupObserver()
        return binding.root
    }

    private fun setupObserver() {
        eventViewModel.selectedEvent?.observe(viewLifecycleOwner, {
            event = it
            setupUi()
            Log.d("Sohel", "setupObserver: $it")
        })
    }

    private fun setupUi() {
        binding.title.setText(event.title).toString()
        binding.description.text = event.description
        binding.status.text = event.status
        binding.timestamp.text = event.timeStamp
        Log.d("bundle", "setupUi: ${event.title}")
    }
}