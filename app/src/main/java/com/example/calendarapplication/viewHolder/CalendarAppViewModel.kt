package com.example.calendarapplication.viewHolder

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.calendarapplication.dataModels.CalendarTaskListResponse
import com.example.calendarapplication.dataModels.Tasks
import com.example.calendarapplication.di.DaggerApiComponent
import com.example.calendarapplication.repository.CalendarAppRepository
import com.google.gson.Gson
import com.google.gson.JsonObject
import dagger.internal.DaggerCollections
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

import javax.inject.Inject

class CalendarAppViewModel:ViewModel() {

    @Inject
    lateinit  var calendarAppRepository: CalendarAppRepository
    init {
        DaggerApiComponent.create().inject(this)
    }

    var taskStored=MutableLiveData<Boolean?>()
    var taskDeleted=MutableLiveData<Boolean?>()
    var calendarTaskListResponse=MutableLiveData<CalendarTaskListResponse?>()

    fun storeCalendarTask(userId:Int,task: Tasks){
        viewModelScope.launch(Dispatchers.IO) {
            val requestBody=getReqBodyForStoringTask(userId,task)
            val response=calendarAppRepository.storeCalendarTask(requestBody)
            if(response.has("status") && response.get("status") is String){
                if(response.getString("status").equals("success",true))taskStored.postValue(true)
                else taskStored.postValue(false)
            }
        }
    }

    private fun getReqBodyForStoringTask(userId:Int, task: Tasks):JsonObject{
        var requestBody=JsonObject()
        requestBody.apply {
            addProperty("user_id",userId)
            addProperty("task",Gson().toJson(task))
        }
        return requestBody
    }

    fun deleteCalendarTask(userId:Int,taskId:Int){
        viewModelScope.launch(Dispatchers.IO) {
            val requestBody=getReqBodyForDeletingTask(userId,taskId)
            val response=calendarAppRepository.deleteCalendarTask(requestBody)
            if(response.has("status") && response.get("status") is String){
                if(response.getString("status").equals("success",true))taskDeleted.postValue(true)
                else taskDeleted.postValue(false)
            }
        }
    }

    private fun getReqBodyForDeletingTask(userId:Int, taskId:Int):JsonObject{
        var requestBody=JsonObject()
        requestBody.apply {
            addProperty("user_id",userId)
            addProperty("task_id",taskId)
        }
        return requestBody
    }

    fun getCalendarTaskList(userId:Int){
        viewModelScope.launch(Dispatchers.IO) {
            val requestBody=getReqBodyForTaskList(userId)
            val response=calendarAppRepository.getCalendarTaskList(requestBody)
            calendarTaskListResponse.postValue(response)
        }
    }

    private fun getReqBodyForTaskList(userId:Int):JsonObject{
        var requestBody=JsonObject()
        requestBody.apply {
            addProperty("user_id",userId)
        }
        return requestBody
    }
}