package com.example.calendarapplication.intrectionInterface

import com.example.calendarapplication.dataModels.Task

interface CalendarViewInteraction {

    fun onCellClicked(position:Int,dayText:String)
}