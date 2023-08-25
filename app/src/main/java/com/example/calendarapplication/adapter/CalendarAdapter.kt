package com.example.calendarapplication.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.calendarapplication.R
import com.example.calendarapplication.databinding.CalendarCellBinding
import com.example.calendarapplication.intrectionInterface.CalendarViewInteraction
import com.example.calendarapplication.viewHolder.CalendarViewHolder

class CalendarAdapter(private val interaction: CalendarViewInteraction):
    RecyclerView.Adapter<CalendarViewHolder>() {
    var daysOfMonth=ArrayList<String>()
    var selectedDay:String?=null

    fun setData(selectedDay :String?,daysList:ArrayList<String>){
        this.daysOfMonth=daysList
        this.selectedDay=selectedDay
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CalendarViewHolder {
        val view: CalendarCellBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.calendar_cell, parent, false)
        val layoutParams: ViewGroup.LayoutParams = view.root.layoutParams
        layoutParams.height = (parent.height * 0.166666666).toInt()
        return CalendarViewHolder(interaction, view)
    }

    override fun onBindViewHolder(holder: CalendarViewHolder, position: Int) {
        var isHighlighted=false
        if(daysOfMonth[position] == selectedDay)isHighlighted=true
        holder.bind(daysOfMonth[position],isHighlighted)

    }

    override fun getItemCount(): Int {
        return daysOfMonth.size
    }
}