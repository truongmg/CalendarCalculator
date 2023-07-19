package com.truong.calendar.service

import com.truong.calendar.*
import com.truong.calendar.data.DataProvider
import java.time.LocalDate

abstract class CalendarCalculator(var dataProvider: DataProvider) {

  // holiday data for a selected country
  lateinit var publicHolidays: List<String>

  fun loadHolidayCalendar() {
    publicHolidays = dataProvider.loadData()
  }

  fun checkDate(date: String?) {
    if (!isValidDate(date)) {
      return
    }

    if (isPublicHoliday(date!!)) {
      println("$date is public holiday")
      return
    }

    if (isWorkingDay(date)) {
      println("$date is working day")
    } else {
      println("$date is weekend")
    }
  }

  fun nextBusinessDay(date: String?): LocalDate? {
    if (!isValidDate(date)) {
      return null
    }

    val localDate = parseDateString(date)
    var nextDay = localDate.plusDays(1)

    while (!isWorkingDay(nextDay) || isPublicHoliday(nextDay.toString()) ) {
      nextDay = nextDay.plusDays(1)
    }

    return nextDay
  }

  private fun isPublicHoliday(date: String): Boolean {
    val formattedDate = date.replace("-", "")
    return publicHolidays.contains(formattedDate)
  }

}

