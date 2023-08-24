package com.example.calendarapplication.dataModels

import com.google.gson.annotations.SerializedName

data class Task(
    @SerializedName("task_id"     ) var taskId     : Int?        = null,
    @SerializedName("task_detail" ) var taskDetail : TaskDetail? = null
)
