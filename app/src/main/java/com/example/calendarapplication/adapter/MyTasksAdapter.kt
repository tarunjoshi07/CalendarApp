package com.example.calendarapplication.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.calendarapplication.R
import com.example.calendarapplication.dataModels.Tasks
import com.example.calendarapplication.viewHolder.TaskViewHolder

class MyTasksAdapter:RecyclerView.Adapter<TaskViewHolder>() {
    var myTasks=ArrayList<Tasks>()

    fun setTasks(taskList:ArrayList<Tasks>){
        this.myTasks=taskList
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)= TaskViewHolder (
        DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.task_layout, parent, false)
    )

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.bind(myTasks[position])
    }

    override fun getItemCount(): Int {
       return myTasks.size
    }
}