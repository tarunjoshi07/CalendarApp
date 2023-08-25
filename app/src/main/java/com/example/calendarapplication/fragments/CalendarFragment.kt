package com.example.calendarapplication.fragments
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.calendarapplication.*
import com.example.calendarapplication.adapter.CalendarAdapter
import com.example.calendarapplication.dataModels.TaskDetail
import com.example.calendarapplication.databinding.FragmentCalendarBinding
import com.example.calendarapplication.intrectionInterface.CalendarViewInteraction
import com.example.calendarapplication.viewModel.CalendarAppViewModel
import java.time.LocalDate
import java.time.YearMonth
import java.util.ArrayList

class CalendarFragment : Fragment(),CalendarViewInteraction {

    private val viewModel by lazy {
        ViewModelProvider(requireActivity())[CalendarAppViewModel::class.java]
    }
    var selectedDate: LocalDate? = null
    var selectedDay:String?=null
    private var binding: FragmentCalendarBinding?=null
    private val calendarAdapter by lazy {
        CalendarAdapter(this)
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?{
        binding= FragmentCalendarBinding.inflate(inflater, container, false)
        return binding?.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpViews()
        setUpOnClickListener()
        setUpObservable()
        setUpRecyclerView()
        setCalenderView()
    }
    private fun setUpViews() {
        selectedDate = LocalDate.now()
        selectedDate?.let {
            selectedDay = dayFromDate(it)
        }
    }
    private fun setUpObservable(){
        viewModel.taskStored.observe(viewLifecycleOwner,{
            if(it==true) {
                Toast.makeText(requireContext(), "Task is added", Toast.LENGTH_SHORT).show()
            }
        })
    }
    private fun setUpOnClickListener(){
        binding?.calendarLayout?.prevMonth?.setOnClickListener {
            selectedDate = selectedDate?.minusMonths(1)
            setCalenderView()
        }
        binding?.calendarLayout?.prevYear?.setOnClickListener {
            selectedDate = selectedDate?.minusYears(1)
            setCalenderView()
        }
        binding?.calendarLayout?.nextMonth?.setOnClickListener {
            selectedDate = selectedDate?.plusMonths(1)
            setCalenderView()
        }
        binding?.calendarLayout?.nextYear?.setOnClickListener {
            selectedDate = selectedDate?.plusYears(1)
            setCalenderView()
        }
        binding?.newTaskLayout?.addTaskButton?.setOnClickListener {
            saveNewTask()
        }
    }

    private fun setUpRecyclerView(){
        val gridlayoutManager = GridLayoutManager(requireContext(), 7)
        binding?.calendarRecyclerView?.apply {
            layoutManager = gridlayoutManager
            adapter = calendarAdapter
        }
    }

    private fun setCalenderView() {
        selectedDate?.let {
            binding?.calendarLayout?.monthTV?.text=monthFromDate(it)
            binding?.calendarLayout?.YearTV?.text=yearFromDate(it)
            val daysInMonth: ArrayList<String> = daysInMonthArray(it)
            binding?.newTaskLayout?.dateText?.text=selectedDay+"-"+getMonthAndYearCurrentDate(it)
            calendarAdapter.setData(selectedDay,daysInMonth)
        }
    }

    private fun daysInMonthArray(date: LocalDate): ArrayList<String>{
        val daysInMonthArray = ArrayList<String>()
        val yearMonth = YearMonth.from(date)
        val daysInMonth = yearMonth.lengthOfMonth()
        val firstOfMonth = selectedDate!!.withDayOfMonth(1)
        val dayOfWeek = firstOfMonth.dayOfWeek.value
        for (i in 1..42) {
            if (i <= dayOfWeek || i > daysInMonth + dayOfWeek) {
                daysInMonthArray.add("")
            } else {
                daysInMonthArray.add((i - dayOfWeek).toString())
            }
        }
        return daysInMonthArray
    }

    override fun onCellClicked(position: Int, dayText: String) {
        selectedDay=dayText
        setCalenderView()
    }

    private fun saveNewTask(){
        val title=binding?.newTaskLayout?.titleText?.text.toString()
        val desc=binding?.newTaskLayout?.descText?.text.toString()
        val taskDate=binding?.newTaskLayout?.dateText?.text.toString()
        val currentDate= getCurrentDate()
        if(title.isNotEmpty() && desc.isNotEmpty()) {
            val task = TaskDetail(
                title = title,
                description = desc,
                createdDate = currentDate,
                taskDate = taskDate
            )
            viewModel.storeCalendarTask(task)
            resetFields()
        }
        else{
            Toast.makeText(requireContext(),"title or desc is empty",Toast.LENGTH_SHORT).show()
        }
    }

    private fun resetFields(){
        binding?.newTaskLayout?.apply{
            titleText.text.clear()
            descText.text.clear()
        }
    }
}