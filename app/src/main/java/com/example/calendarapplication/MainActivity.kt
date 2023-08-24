package com.example.calendarapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.example.calendarapplication.adapter.ViewPagerAdapter
import com.example.calendarapplication.databinding.ActivityMainBinding
import com.example.calendarapplication.fragments.CalendarFragment
import com.example.calendarapplication.fragments.TasksFragment
import com.example.calendarapplication.viewModel.CalendarAppViewModel

class MainActivity : AppCompatActivity() {
    private val viewModel by lazy {
        ViewModelProvider(this)[CalendarAppViewModel::class.java]
    }
    private val mainView: ActivityMainBinding? by lazy {
        DataBindingUtil.setContentView(this, R.layout.activity_main)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setUpViewPager()
        setUpClickListener()
    }

    private fun setUpViewPager(){
        val myAdapter = ViewPagerAdapter(supportFragmentManager,lifecycle)
        myAdapter.addFragment(CalendarFragment())
        myAdapter.addFragment(TasksFragment())
        mainView?.viewPager2?.apply {
            adapter=myAdapter
            orientation = ViewPager2.ORIENTATION_HORIZONTAL
        }
    }

    private fun setUpClickListener(){
        mainView?.calendarLayout?.setOnClickListener(View.OnClickListener {
            mainView?.viewPager2?.currentItem=0
        })
        mainView?.tasksLayout?.setOnClickListener(View.OnClickListener {
            mainView?.viewPager2?.currentItem=1
        })
    }
}