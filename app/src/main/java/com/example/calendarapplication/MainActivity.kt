package com.example.calendarapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.calendarapplication.adapter.ViewPagerAdapter
import com.example.calendarapplication.databinding.ActivityMainBinding
import com.example.calendarapplication.fragments.CalendarFragment
import com.example.calendarapplication.fragments.TasksFragment
import com.example.calendarapplication.viewModel.CalendarAppViewModel
import com.google.android.material.tabs.TabLayoutMediator
import java.time.LocalDate
import java.util.ArrayList

class MainActivity : AppCompatActivity() {
    private val viewModel by lazy {
        ViewModelProvider(this)[CalendarAppViewModel::class.java]
    }
    private val mainView: ActivityMainBinding? by lazy {
        DataBindingUtil.setContentView(this, R.layout.activity_main)
    }

    private val tabArray = arrayOf("Calendar", "My Tasks",)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setUpViewPager()
        setUpTabLayout()
        setUpObservable()
    }

    private  fun setUpObservable(){
        viewModel.showError?.observe(this, {
            if(it==true){
                Toast.makeText(this,"Something went wrong",Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun setUpTabLayout() {
        if (mainView?.tabLayout != null && mainView?.viewPager2 != null) {
            TabLayoutMediator(mainView?.tabLayout!!, mainView?.viewPager2!!) { tab, position ->
                tab.text = tabArray[position]
            }.attach()
        }
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
}