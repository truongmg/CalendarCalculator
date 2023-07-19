package com.truong.calendar

import java.lang.NumberFormatException
import kotlin.system.exitProcess

fun main(args: Array<String>) {
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

  // factory to create concrete calendar class
  val calendarCalculator = CalendarCalculator().apply {
    loadHolidayCalendar()
    setHolidayCalendar(holidayOption)
  }

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
