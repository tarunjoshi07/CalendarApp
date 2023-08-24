package com.example.calendarapplication.apiInterface

import com.example.calendarapplication.dataModels.CalendarTaskListResponse
import com.google.gson.JsonObject
import org.json.JSONObject
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface CalendarAppApiInterface {
    @POST("api/storeCalendarTask")
    suspend  fun storeCalendarTask(@Body requestBody: JsonObject): Response<JSONObject>

    @POST("api/deleteCalendarTask")
    suspend  fun deleteCalendarTask(@Body requestBody: JsonObject): Response<JSONObject>

    @POST("api/getCalendarTaskList")
    suspend  fun getCalendarTaskList(@Body requestBody: JsonObject): Response<CalendarTaskListResponse>
}