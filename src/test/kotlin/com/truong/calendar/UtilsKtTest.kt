package com.truong.calendar

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import java.time.format.DateTimeParseException

internal class UtilsKtTest {

  @Test
  fun testReadResourceFileAsLines() {
    val path = "/data/test.txt"
    val content = readResourceFileAsLines(path)
    assertNotNull(content, "should be able to retrieve the content")
    assertEquals(content, "Sample test", "content should be the same")
  }

  @Test
  fun testReadResourceFileAsLines_fileNotExist() {
    val path = "/data/notfound.txt"
    val content = readResourceFileAsLines(path)
    assertNull(content, "content should be null as file not exist")
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
  fun testParseDateString() {
    val localDate = parseDateString("20210102")
    assertTrue(localDate.monthValue == 1, "a valid date should be able to parsed")
    assertTrue(localDate.year == 2021, "a valid date should be able to parsed")
  }

  @Test
  fun testParseDateString_dateWithOtherFormat() {
    assertThrows(DateTimeParseException::class.java) {
      parseDateString("2021-01-02")
    }
  }

  @Test
  fun isWorkingDay_falseForSaturday() {
    assertFalse(isWorkingDay("20230603"), "20230603 is a weekend")
  }

  @Test
  fun isWorkingDay_falseForSunday() {
    assertFalse(isWorkingDay("20230604"), "20230604 is a weekend")
  }

  @Test
  fun testIsWorkingDay() {
    assertTrue(isWorkingDay("20230605"), "20230605 is a working day")
  }
}