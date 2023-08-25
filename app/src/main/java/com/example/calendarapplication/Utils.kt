package com.example.calendarapplication

import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*


fun monthFromDate(date: LocalDate): String? {
    val formatter = DateTimeFormatter.ofPattern("MMMM")
    return date.format(formatter)
}

fun yearFromDate(date: LocalDate): String? {
    val formatter = DateTimeFormatter.ofPattern("yyyy")
    return date.format(formatter)
}

fun dayFromDate(date: LocalDate): String? {
    val formatter = DateTimeFormatter.ofPattern("dd")
    return date.format(formatter)
}

fun getCurrentDate(): String? {
    var dateFormat = SimpleDateFormat("dd-MM-yyyy");
    return dateFormat.format(Calendar.getInstance().time);
}

fun getMonthAndYearCurrentDate(date: LocalDate): String? {
    val formatter = DateTimeFormatter.ofPattern("MM-yyyy")
    return date.format(formatter)
}