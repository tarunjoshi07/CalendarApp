package com.example.calendarapplication.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.calendarapplication.adapter.MyTasksAdapter
import com.example.calendarapplication.intrectionInterface.RecyclerViewInteraction
import com.example.calendarapplication.dataModels.Task
import com.example.calendarapplication.dataModels.TaskDetail
import com.example.calendarapplication.databinding.FragmentTasksBinding
import com.example.calendarapplication.viewModel.CalendarAppViewModel

class TasksFragment : Fragment(), RecyclerViewInteraction {
    private val viewModel by lazy {
        ViewModelProvider(requireActivity())[CalendarAppViewModel::class.java]
    }
    private val myTasksAdapter by lazy{
        MyTasksAdapter(this)
    }
    private var binding: FragmentTasksBinding?=null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View{
         binding= FragmentTasksBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getCalendarTaskList(550)
        setUpRecyclerView()
        setUpObservables()
        saveData()
    }

    fun saveData(){
        var task=TaskDetail()
        task.title="tatu"
        task.description="papi guu khata hai"
        task.taskDate="24-08-2022"
        viewModel.storeCalendarTask(550,task)
    }

    private fun  setUpObservables(){
        viewModel.taskList.observe(viewLifecycleOwner, Observer { taskList ->
            taskList?.tasks?.let {
                myTasksAdapter.setTasks(it)
            }
        })
    }

    private fun setUpRecyclerView() {
        binding?.taskRecyclerView?.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = myTasksAdapter
            addItemDecoration(DividerItemDecoration(this.context, DividerItemDecoration.VERTICAL))
        }
    }

    override fun onTaskClicked(task: Task) {
        viewModel.deleteCalendarTask(550,task.taskId)
    }
}