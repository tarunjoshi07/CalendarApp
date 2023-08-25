package com.example.calendarapplication.viewHolder

import androidx.recyclerview.widget.RecyclerView
import com.example.calendarapplication.dataModels.Task
import com.example.calendarapplication.databinding.TaskLayoutBinding
import com.example.calendarapplication.intrectionInterface.RecyclerViewInteraction

class TaskViewHolder(private val interaction: RecyclerViewInteraction, private val view: TaskLayoutBinding):RecyclerView.ViewHolder(view.root){
    var task:Task?=null
    init{
        setOnClickListener()
    }
    private fun setOnClickListener(){
        view.deleteTask.setOnClickListener {
            task?.let {
                interaction.onTaskClicked(it)
            }
        }
    }
    fun bind(task: Task){
        this.task=task
        view.taskTitle.text="Title : ${task.taskDetail?.title}"
        view.taskDesc.text= "Desc : ${task.taskDetail?.description}"
        view.taskDate.text= "Date : ${task.taskDetail?.taskDate}"
    }
}