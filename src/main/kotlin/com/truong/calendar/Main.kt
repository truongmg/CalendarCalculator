package com.truong.calendar

import com.truong.calendar.data.FileDataProvider
import com.truong.calendar.factory.CalendarFactory
import com.truong.calendar.service.CalendarCalculator
import java.lang.NumberFormatException
import kotlin.system.exitProcess

const val CHECK_A_DATE = 1
const val CALCULATE_NEXT_BIZ_DAY = 2

fun main(args: Array<String>) {
  // Select country
  val countryOption = userSelectAction {
    println(
      """
        Welcome to GIC Calendar Calculator
        Please choose a country:
        [1] SG
        [2] HK
      """.trimIndent()
    )
  }

  // use factory to create concrete calendar class
  val calendarCalculator = CalendarFactory.createCalendarCalculator(
    countryOption, FileDataProvider()
  ).apply {
    loadHolidayCalendar()
  }
  println()

  // select action
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

  if (action == CHECK_A_DATE) {
    checkDateAction(calendarCalculator)
  } else if (action == CALCULATE_NEXT_BIZ_DAY) {
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
