package com.example.calendarapplication.viewHolder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.calendarapplication.dataModels.Tasks
import com.example.calendarapplication.databinding.TaskLayoutBinding

class TaskViewHolder(private val view: TaskLayoutBinding):RecyclerView.ViewHolder(view.root){
    init{
        setOnClickListener()
    }
    private fun setOnClickListener(){
        view.deleteTask.setOnClickListener {

        }
    }
    fun bind(task: Tasks){
        view.taskTitle.text=task.taskDetail?.title
        view.taskDesc.text=task.taskDetail?.description
        view.taskDate.text=task.taskDetail?.taskDate
    }
}