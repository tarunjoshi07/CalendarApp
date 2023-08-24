package com.example.calendarapplication.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.calendarapplication.dataModels.CalendarTaskListResponse
import com.example.calendarapplication.dataModels.Task
import com.example.calendarapplication.dataModels.TaskDetail
import com.example.calendarapplication.di.DaggerApiComponent
import com.example.calendarapplication.repository.CalendarAppRepository
import com.google.gson.Gson
import com.google.gson.JsonObject
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
    var taskList=MutableLiveData<CalendarTaskListResponse?>()

    fun storeCalendarTask(userId:Int,task: TaskDetail){
        viewModelScope.launch(Dispatchers.IO) {
            val requestBody=getReqBodyForStoringTask(userId,task)
            val response=calendarAppRepository.storeCalendarTask(requestBody)
            if(response.isSuccessful && response.body()!=null) {
                taskStored.postValue(true)
                getCalendarTaskList(userId)
            }
        }
    }

    private fun getReqBodyForStoringTask(userId:Int, task: TaskDetail):JsonObject{
        var requestBody=JsonObject()
        var taskJson=JsonObject()
        taskJson.apply {
            if(task.title!=null)this.addProperty("title",task.title)
            if(task.description!=null)this.addProperty("description",task.description)
            if(task.createdDate!=null)this.addProperty("created_date",task.createdDate)
            if(task.taskDate!=null)this.addProperty("task_date",task.taskDate)
        }
        requestBody.apply {
            addProperty("user_id",userId)
            add("task",taskJson)
        }
        return requestBody
    }

    fun deleteCalendarTask(userId:Int,taskId:Int?){
        viewModelScope.launch(Dispatchers.IO) {
            val requestBody=getReqBodyForDeletingTask(userId,taskId)
            val response=calendarAppRepository.deleteCalendarTask(requestBody)
            if(response.isSuccessful && response.body()!=null) {
                taskDeleted.postValue(true)
                getCalendarTaskList(userId)
            }
        }
    }

    private fun getReqBodyForDeletingTask(userId:Int, taskId:Int?):JsonObject{
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
            if(response.isSuccessful && response.body()!=null) {
                taskList.postValue(response.body())
            }
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