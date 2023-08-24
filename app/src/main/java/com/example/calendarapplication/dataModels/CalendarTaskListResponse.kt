package com.example.calendarapplication.dataModels

import com.google.gson.annotations.SerializedName

data class CalendarTaskListResponse(
    @SerializedName("tasks" ) var tasks : ArrayList<Tasks> = arrayListOf()
)

