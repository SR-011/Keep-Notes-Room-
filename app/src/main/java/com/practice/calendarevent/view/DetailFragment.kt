package com.practice.calendarevent.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.practice.calendarevent.R
import com.practice.calendarevent.data.model.Event
import com.practice.calendarevent.databinding.FragmentDetailBinding

class DetailFragment : Fragment() {
    private lateinit var binding: FragmentDetailBinding
    private lateinit var event: Event

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        arguments?.let {
            event = it.getParcelable("key")!!
            Log.d("bundle", "onCreateView: $event")
        }
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_detail, container, false)
        setupUi()
        return binding.root
    }

    private fun setupUi() {
        binding.title.setText(event.title).toString()
        binding.description.text = event.description
        Log.d("bundle", "setupUi: ${event.title}")
    }
}