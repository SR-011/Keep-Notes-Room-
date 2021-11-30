package com.practice.calendarevent.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.practice.calendarevent.R
import com.practice.calendarevent.data.model.Event
import com.practice.calendarevent.databinding.ItemRowBinding

class EventAdapter(private val listener: OnEventClickListener, private val eventList: ArrayList<Event>) :
    RecyclerView.Adapter<EventAdapter.ViewHolder>() {

    class ViewHolder(private val binding: ItemRowBinding, private val listener: OnEventClickListener) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(event: Event) {
            binding.variable = event
            binding.root.setOnClickListener {
                listener.onEventClick(event)
            }
            binding.delete.setOnClickListener {
                listener.onDeleteIconClick(event)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_row,
                parent,
                false
            ),listener
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(eventList[position])
    }

    override fun getItemCount(): Int = eventList.size

    interface OnEventClickListener {
        fun onEventClick(event: Event)
        fun onDeleteIconClick(event: Event)
    }
}