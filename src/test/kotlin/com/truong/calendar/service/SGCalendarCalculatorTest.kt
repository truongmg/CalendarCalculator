package com.truong.calendar.service

import com.truong.calendar.data.DataProvider
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock

internal class SGCalendarCalculatorTest {

  @Test
  fun testLoadSGPublicHoliday() {
    val mockDataProvider = mock<DataProvider> {
      on { loadData() } doReturn mockSgHolidays()
    }

    val sgCalendar = SGCalendarCalculator(mockDataProvider)
      .apply { loadHolidayCalendar() }

    assertTrue(sgCalendar.publicHolidays.isNotEmpty(), "SG public holidays should be loaded")
    assertTrue(sgCalendar.publicHolidays.contains("20230102"), "SG public holidays should have date 20230102")
  }

  @Test
  fun testLoadSGPublicHoliday_withNoData() {
    val mockDataProvider = mock<DataProvider> {
      on { loadData() } doReturn emptyList()
    }

    val sgCalendar = SGCalendarCalculator(mockDataProvider)
      .apply { loadHolidayCalendar() }

    assertTrue(sgCalendar.publicHolidays.isEmpty(), "No data provided, SG public holidays should be empty")
  }

  private fun mockSgHolidays() = listOf(
    "20230102", "20230201", "20230202", "20230407", "20230422", "20230501", "20230602",
    "20230629", "20230809", "20231112", "20231225"
  )

}