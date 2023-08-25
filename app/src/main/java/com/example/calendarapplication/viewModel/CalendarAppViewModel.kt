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
import java.lang.Exception

import javax.inject.Inject

class CalendarAppViewModel:ViewModel() {

    @Inject
    lateinit  var calendarAppRepository: CalendarAppRepository
    init {
        DaggerApiComponent.create().inject(this)
    }

    private val userId=550
    var taskStored=MutableLiveData<Boolean?>()
    var taskDeleted=MutableLiveData<Boolean?>()
    var taskList=MutableLiveData<CalendarTaskListResponse?>()

    fun storeCalendarTask(task: TaskDetail){
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val requestBody = getReqBodyForStoringTask(task)
                val response = calendarAppRepository.storeCalendarTask(requestBody)
                if (response.isSuccessful && response.body() != null) {
                    taskStored.postValue(true)
                    getCalendarTaskList()
                }
            }catch (e:Exception){

            }
        }
    }

    private fun getReqBodyForStoringTask(task: TaskDetail):JsonObject{
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

    fun deleteCalendarTask(taskId:Int?){
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val requestBody = getReqBodyForDeletingTask(taskId)
                val response = calendarAppRepository.deleteCalendarTask(requestBody)
                if (response.isSuccessful && response.body() != null) {
                    taskDeleted.postValue(true)
                    getCalendarTaskList()
                }
            }catch (e:Exception){

            }
        }
    }

    private fun getReqBodyForDeletingTask(taskId:Int?):JsonObject{
        var requestBody=JsonObject()
        requestBody.apply {
            addProperty("user_id",userId)
            addProperty("task_id",taskId)
        }
        return requestBody
    }

    fun getCalendarTaskList(){
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val requestBody = getReqBodyForTaskList(userId)
                val response = calendarAppRepository.getCalendarTaskList(requestBody)
                if (response.isSuccessful && response.body() != null) {
                    taskList.postValue(response.body())
                }
            }catch (e:Exception){

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