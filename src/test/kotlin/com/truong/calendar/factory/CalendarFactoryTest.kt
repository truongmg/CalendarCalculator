package com.truong.calendar.factory

import com.truong.calendar.service.HKCalendarCalculator
import com.truong.calendar.service.SGCalendarCalculator
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class CalendarFactoryTest {

  @Test
  fun testCreateSgCalendar() {
    val sgOption = 1
    val calendarCalculator = CalendarFactory.createCalendarCalculator(countryOption = sgOption)
    assertNotNull(calendarCalculator)
    assertTrue(calendarCalculator is SGCalendarCalculator, "Instance of SG Calendar Calculator should be created")
  }

  @Test
  fun testCreateHkCalendar() {
    val hkOption = 2
    val calendarCalculator = CalendarFactory.createCalendarCalculator(countryOption = hkOption)
    assertNotNull(calendarCalculator)
    assertTrue(calendarCalculator is HKCalendarCalculator, "Instance of HK Calendar Calculator should be created")
  }

}