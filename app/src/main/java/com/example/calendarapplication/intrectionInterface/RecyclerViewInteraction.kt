package com.example.calendarapplication.intrectionInterface

import com.example.calendarapplication.dataModels.Task

interface RecyclerViewInteraction {
    fun onTaskClicked(task:Task)
}