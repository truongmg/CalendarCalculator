import java.lang.NumberFormatException
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import kotlin.system.exitProcess

class CalendarCalculator {

  private var fileMap = mapOf(
    "SG" to "/data/SG.txt",
    "HK" to "/data/HK.txt"
  )
  var holidayData = HashMap<String, List<String>>()
  lateinit var selectedHoliday: List<String>

  fun loadHolidayCalendar() {
    fileMap.forEach { (key, value) ->
      val fileContent = readResourceFileAsLines(value)
      assert(fileContent != null)
      holidayData[key] = fileContent!!.splitToList("\n")
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

  private fun isWorkingDay(date: String): Boolean {
    val localDate = parseDateString(date)
    val dayOfWeek = localDate.dayOfWeek
    return dayOfWeek != DayOfWeek.SATURDAY && dayOfWeek != DayOfWeek.SUNDAY
  }


  private fun isWorkingDay(localDate: LocalDate): Boolean {
    val dayOfWeek = localDate.dayOfWeek
    return dayOfWeek != DayOfWeek.SATURDAY && dayOfWeek != DayOfWeek.SUNDAY
  }

  private fun parseDateString(date: String?): LocalDate
      = LocalDate.parse(date, DateTimeFormatter.BASIC_ISO_DATE)

  private fun isValidDate(date: String?): Boolean {
    if (date == null) return false

    val dateRegex = Regex("^\\d{4}\\d{2}\\d{2}$")
    if (!dateRegex.matches(date)) return false

    try {
      LocalDate.parse(date, DateTimeFormatter.BASIC_ISO_DATE)
    } catch (e: Exception) {
      return false
    }

    return true
  }


}

fun main(args: Array<String>) {
  val calendarCalculator = CalendarCalculator().apply {
    loadHolidayCalendar()
  }

  val holidayOption = userSelectAction {
    println(
      """
        Welcome to GIC Calendar Calculator
        Please choose a country:
        [1] SG
        [2] HK
      """.trimIndent()
    )
  }
  calendarCalculator.setHolidayCalendar(holidayOption)
  println()

  val action = userSelectAction {
    println(
      """
        Please choose an action:
        [1] Check a date
        [2] Calculate next business day
      """.trimIndent()
    )
  }
  println()

  if (action == 1) {
    checkDateAction(calendarCalculator)
  } else {
    calculateNextBizDay(calendarCalculator)
  }

}

fun calculateNextBizDay(calendarCalculator: CalendarCalculator) {
  println("Please enter a date in yyyyMMdd format")
  do {
    val date = readLine()
    if (date.isNullOrEmpty()) {
      println("Please enter a date in yyyyMMdd format or please enter # to end")
      continue
    }

    if (date == "#") exitProcess(1)

    val nextBusinessDay = calendarCalculator.nextBusinessDay(date)
    println("The next business day is $nextBusinessDay")
    println("Please enter a date in yyyyMMdd format or please enter # to end")
  } while (true)
}

fun checkDateAction(calendarCalculator: CalendarCalculator) {
  println("Please enter a date to check in yyyyMMdd format")
  do {
    val date = readLine()
    if (date.isNullOrEmpty()) {
      println("Please enter a date to check in yyyyMMdd format or please enter # to end")
      continue
    }

    if (date == "#") exitProcess(1)

    calendarCalculator.checkDate(date)
    println("Please enter a date to check in yyyyMMdd format or please enter # to end")
  } while (true)
}

fun userSelectAction(greeting: () -> Unit): Int {
  do {
    greeting()
    try {
      return when(val input = readLine()?.toInt()) {
        1, 2 -> input
        else -> {
          promptRetryMessage()
          continue
        }
      }

    } catch (e: NumberFormatException) {
      promptRetryMessage()
    }
  } while (true)
}

private fun promptRetryMessage() {
  println("Please enter 1 or 2 to continue\n")
}

