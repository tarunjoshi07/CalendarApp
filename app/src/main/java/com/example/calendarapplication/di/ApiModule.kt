package com.example.calendarapplication.di

import com.example.calendarapplication.apiInterface.CalendarAppApiInterface
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class ApiModule {

    private val BASE_URL = "https://dev.frndapp.in:8080/"

    @Provides
    fun provideRetrofitObject(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    fun provideCalendarAppApiInterface(retrofit: Retrofit):CalendarAppApiInterface{
        return retrofit.create(CalendarAppApiInterface::class.java)
    }
}