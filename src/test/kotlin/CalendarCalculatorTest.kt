import org.junit.jupiter.api.Test
import kotlin.test.assertTrue

class CalendarCalculatorTest {

  @Test
  fun loadSingaporeHolidayCalendarTest() {
    var calendarCalculator = loadHolidayCalendar()
    var holidayMap = calendarCalculator.holidayMap

    val sgHolidays = listOf(
      "20230102",
      "20230201",
      "20230202",
      "20230407",
      "20230422",
      "20230501",
      "20230602",
      "20230629",
      "20230809",
      "20231112",
      "20231225"
    )

    verifyHolidaysFor(
      holidayMap,
      "SG",
      sgHolidays
    )
  }

  @Test
  fun loadHongKongHolidayCalendarTest() {
    var calendarCalculator = loadHolidayCalendar()
    var holidayMap = calendarCalculator.holidayMap

    val hkHolidays = listOf("20230102", "20230123", "20230124", "20230125", "20230405", "20230407", "20230408", "20230410", "20230501", "20230526", "20230622", "20230701", "20230930", "20231002", "20231023", "20231225", "20231226")
    verifyHolidaysFor(
      holidayMap,
      "HK",
      hkHolidays
    )
  }

  private fun loadHolidayCalendar(): CalendarCalculator {
    var calendarCalculator = CalendarCalculator()
    calendarCalculator.loadHolidayCalendar()
    assertTrue("Holiday Map should be loaded") { calendarCalculator.holidayMap.isNotEmpty() }
    return calendarCalculator
  }

  private fun verifyHolidaysFor(holidayMap: HashMap<String, List<String>>, country: String, expectedHolidays: List<String> ) {
    assertTrue("$country holidays should be loaded") {
      !holidayMap[country].isNullOrEmpty()
    }

    assertTrue("$country holidays should contain all these dates") {
      holidayMap[country]!!.containsAll(expectedHolidays)
    }
  }


}