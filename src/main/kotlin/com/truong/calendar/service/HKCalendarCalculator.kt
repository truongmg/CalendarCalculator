package com.truong.calendar.service

import com.truong.calendar.data.DataProvider

class HKCalendarCalculator(dataProvider: DataProvider) : CalendarCalculator(dataProvider) {

  companion object {
    const val PUBLIC_HOLIDAY_PATH = "/data/HK.txt"
  }

  override fun loadHolidayCalendar() {
    publicHolidays = dataProvider.loadData(PUBLIC_HOLIDAY_PATH)
  }

}