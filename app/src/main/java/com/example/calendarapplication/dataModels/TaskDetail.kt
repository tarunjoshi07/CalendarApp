package com.example.calendarapplication.dataModels

import com.google.gson.annotations.SerializedName


data class TaskDetail(
    @SerializedName("title"       ) var title       : String? = null,
    @SerializedName("description" ) var description : String? = null,
    @SerializedName("created_date") var createdDate : String? = null,
    @SerializedName("task_date"   ) var taskDate    : String? = null,
)
