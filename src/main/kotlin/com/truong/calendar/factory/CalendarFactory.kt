package com.truong.calendar.factory

import com.truong.calendar.data.FileDataProvider
import com.truong.calendar.data.FileLocation
import com.truong.calendar.service.CalendarCalculator
import com.truong.calendar.service.HKCalendarCalculator
import com.truong.calendar.service.SGCalendarCalculator

abstract class CalendarFactory {

  companion object {

    fun createCalendarCalculator(countryOption: Int): CalendarCalculator {
      return when (countryOption) {
        1 -> SGCalendarCalculator(
          FileDataProvider(FileLocation.SG_PUBLIC_HOLIDAY_PATH)
        )
        else -> HKCalendarCalculator(
          FileDataProvider(FileLocation.HK_PUBLIC_HOLIDAY_PATH)
        )
      }
    }
  }


}