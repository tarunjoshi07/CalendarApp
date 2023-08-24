package com.example.calendarapplication.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.calendarapplication.R
import com.example.calendarapplication.adapter.MyTasksAdapter
import com.example.calendarapplication.databinding.FragmentTasksBinding
import com.example.calendarapplication.viewHolder.CalendarAppViewModel

class TasksFragment : Fragment() {
    private val viewModel by lazy {
        ViewModelProvider(requireActivity())[CalendarAppViewModel::class.java]
    }
    private val myTasksAdapter by lazy{
        MyTasksAdapter()
    }
    private var binding: FragmentTasksBinding?=null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View{
         binding= FragmentTasksBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getCalendarTaskList(123)
        setUpRecyclerView()
        setUpObservables()
    }

    private fun  setUpObservables(){

    }

    private fun setUpRecyclerView() {
        binding?.taskRecyclerView?.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = myTasksAdapter
            addItemDecoration(DividerItemDecoration(this.context, DividerItemDecoration.VERTICAL))
        }
    }
}