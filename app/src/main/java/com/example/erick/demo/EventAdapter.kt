package com.example.erick.demo

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_event.view.*

class EventAdapter() : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    val eventList = mutableListOf<Event>()

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_event, parent, false)
        return object : RecyclerView.ViewHolder(view) {}
    }

    fun updateEvent(event: Event) {
        eventList.clear()
        eventList.add(event)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = eventList.size

    override fun onBindViewHolder(itemView: RecyclerView.ViewHolder, p1: Int) {
        with(itemView.itemView){
            txtTime.text = eventList[p1].time
            txtExplanation.text = eventList[p1].explanation
            txtTitle.text = eventList[p1].title
        }

    }
}