package com.example.calendarapplication.di

import com.example.calendarapplication.viewModel.CalendarAppViewModel
import dagger.Component


@Component(modules = [ApiModule::class])
interface ApiComponent {
    fun inject(viewModel: CalendarAppViewModel)
}