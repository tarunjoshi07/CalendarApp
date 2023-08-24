package com.example.calendarapplication.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.calendarapplication.R
import com.example.calendarapplication.dataModels.Task
import com.example.calendarapplication.intrectionInterface.RecyclerViewInteraction
import com.example.calendarapplication.viewHolder.TaskViewHolder

class MyTasksAdapter(private val interaction:RecyclerViewInteraction):RecyclerView.Adapter<TaskViewHolder>() {
    var myTasks=ArrayList<Task>()

    fun setTasks(taskList:ArrayList<Task>){
        this.myTasks=taskList
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)= TaskViewHolder (interaction,
        DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.task_layout, parent, false)
    )

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.bind(myTasks[position])
    }

    override fun getItemCount(): Int {
       return myTasks.size
    }
}