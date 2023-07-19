package com.truong.calendar.service

import com.truong.calendar.data.DataProvider
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock

internal class HKCalendarCalculatorTest {

  @Test
  fun testLoadHKPublicHoliday() {
    val mockDataProvider = mock<DataProvider> {
      on { loadData() } doReturn mockHkHolidays()
    }

    val hkCalendar = HKCalendarCalculator(mockDataProvider)
      .apply { loadHolidayCalendar() }

    assertTrue(hkCalendar.publicHolidays.isNotEmpty(), "HK public holidays should be loaded")
    assertTrue(hkCalendar.publicHolidays.contains("20230124"), "HK public holidays should have date 20230102")
  }

  @Test
  fun testLoadHkPublicHoliday_withNoData() {
    val mockDataProvider = mock<DataProvider> {
      on { loadData() } doReturn emptyList()
    }

    val hkCalendar = HKCalendarCalculator(mockDataProvider)
      .apply { loadHolidayCalendar() }

    assertTrue(hkCalendar.publicHolidays.isEmpty(), "No data provided, HK public holidays should be empty")
  }

  private fun mockHkHolidays() = listOf(
    "20230102", "20230123", "20230124", "20230125", "20230405", "20230407", "20230408",
    "20230410", "20230501", "20230526", "20230622", "20230701", "20230930", "20231002",
    "20231023", "20231225", "20231226"
  )

}