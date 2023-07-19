package com.truong.calendar.service

import com.truong.calendar.data.DataProvider

class SGCalendarCalculator(dataProvider: DataProvider) : CalendarCalculator(dataProvider) {

  companion object {
    const val PUBLIC_HOLIDAY_PATH = "/data/SG.txt"
  }

  override fun loadHolidayCalendar() {
    publicHolidays = dataProvider.loadData(PUBLIC_HOLIDAY_PATH)
  }

}