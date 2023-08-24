package com.example.calendarapplication.repository

import com.example.calendarapplication.apiInterface.CalendarAppApiInterface
import com.example.calendarapplication.dataModels.CalendarTaskListResponse
import com.google.gson.JsonObject
import org.json.JSONObject
import javax.inject.Inject

class CalendarAppRepository@Inject constructor(private val calendarAppApiInterface: CalendarAppApiInterface){

    suspend  fun storeCalendarTask(requestBody: JsonObject): JSONObject{
        return calendarAppApiInterface.storeCalendarTask(requestBody)
    }

    suspend  fun deleteCalendarTask(requestBody: JsonObject): JSONObject{
        return calendarAppApiInterface.deleteCalendarTask(requestBody)
    }

    suspend  fun getCalendarTaskList(requestBody: JsonObject): CalendarTaskListResponse{
        return calendarAppApiInterface.getCalendarTaskList(requestBody)
    }
}