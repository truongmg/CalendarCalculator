package com.truong.calendar.service

import com.truong.calendar.*
import java.time.LocalDate

open class CalendarCalculatorOld() {

  // file path for each country
  private val fileMap = mapOf(
    "SG" to "/data/SG.txt",
    "HK" to "/data/HK.txt"
  )
  // store holiday data in a hashmap
  val holidayData = HashMap<String, List<String>>()
  // holiday data for a selected country
  lateinit var selectedHoliday: List<String>

  fun loadHolidayCalendar() {
    fileMap.forEach { (key, value) ->
      val fileContent = readResourceFileAsLines(value)
      assert(fileContent != null)
      holidayData[key] = fileContent!!.toList("\n")
    }
  }

  fun setHolidayCalendar(option: Int) {
    selectedHoliday = if (option == 1) holidayData["SG"]!! else holidayData["HK"]!!
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
    return selectedHoliday.contains(formattedDate)
  }

}

