package com.truong.calendar

import java.time.DayOfWeek
import java.time.LocalDate
import java.time.format.DateTimeFormatter

fun readResourceFileAsLines(fileName: String): String?
    = object {}.javaClass.getResource(fileName)?.readText()

fun String.toList(delimiters: String): List<String>
    = this.split(delimiters).toList()

fun isValidDate(date: String?): Boolean {
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

fun parseDateString(date: String?): LocalDate
    = LocalDate.parse(date, DateTimeFormatter.BASIC_ISO_DATE)


fun isWorkingDay(date: String): Boolean {
  val localDate = parseDateString(date)
  val dayOfWeek = localDate.dayOfWeek
  return dayOfWeek != DayOfWeek.SATURDAY && dayOfWeek != DayOfWeek.SUNDAY
}


fun isWorkingDay(localDate: LocalDate): Boolean {
  val dayOfWeek = localDate.dayOfWeek
  return dayOfWeek != DayOfWeek.SATURDAY && dayOfWeek != DayOfWeek.SUNDAY
}
