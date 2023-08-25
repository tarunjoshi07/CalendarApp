package com.example.calendarapplication.viewHolder

import android.graphics.Color
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.calendarapplication.R
import com.example.calendarapplication.databinding.CalendarCellBinding
import com.example.calendarapplication.intrectionInterface.CalendarViewInteraction

class CalendarViewHolder (private val interaction: CalendarViewInteraction, private val view: CalendarCellBinding): RecyclerView.ViewHolder(view.root){
    private var day:String?=null
    init{
        setOnClickListener()
    }
    private fun setOnClickListener(){
        view.cellDayText.setOnClickListener {
            day?.let{
                interaction.onCellClicked(adapterPosition,it)
            }
        }
    }
    fun bind(day:String,isHighlighted:Boolean){
        this.day=day
        view.cellDayText.text=day
        if(isHighlighted)view.cellDayText.setTextColor(Color.parseColor("#EE4B2B"))
        else view.cellDayText.setTextColor(ContextCompat.getColor(view.root.context, R.color.black))
    }
}