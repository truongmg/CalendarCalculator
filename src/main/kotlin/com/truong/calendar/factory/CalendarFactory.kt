package com.truong.calendar.factory

import com.truong.calendar.data.DataProvider
import com.truong.calendar.service.CalendarCalculator
import com.truong.calendar.service.HKCalendarCalculator
import com.truong.calendar.service.SGCalendarCalculator

abstract class CalendarFactory {

  companion object {

    fun createCalendarCalculator(countryOption: Int, dataProvider: DataProvider): CalendarCalculator {
      return if (countryOption == 1) SGCalendarCalculator(dataProvider)
      else HKCalendarCalculator(dataProvider)
    }
  }


}