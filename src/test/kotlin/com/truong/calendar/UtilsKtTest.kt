package com.truong.calendar

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class UtilsKtTest {

  @Test
  fun readResourceFileAsLines() {
  }

  @Test
  fun toList() {
  }

  @Test
  fun testIsValidDate_dateIsNull() {
    assertFalse(isValidDate(date = null), "null date should not be a valid date")
  }

  @Test
  fun testIsValidDate_dateWithCharacters() {
    assertFalse(isValidDate("this is a date"), "date with characters should not be a valid date")
  }

  @Test
  fun testIsValidDate_dateWithMoreThan8Digits() {
    assertFalse(isValidDate("202101021"), "valid date should contain no more than 8 digits")
  }

  @Test
  fun testIsValidDate_dateWithLessThan8Digits() {
    assertFalse(isValidDate("2021010"), "valid date should contain no less than 8 digits")
  }

  @Test
  fun testIsValidDate_dateWithInvalidDay() {
    assertFalse(isValidDate("20210150"), "date with day as 50 should not be a valid date")
  }

  @Test
  fun testIsValidDate() {
    assertTrue(isValidDate("20210102"), "a valid date should return true")
  }

  @Test
  fun parseDateString() {
  }

  @Test
  fun isWorkingDay() {
  }

  @Test
  fun testIsWorkingDay() {
  }
}