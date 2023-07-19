package com.truong.calendar.data

import com.truong.calendar.readResourceFileAsLines
import com.truong.calendar.toList

class FileDataProvider(var path: String) : DataProvider {

  override fun loadData(): List<String> {
    val fileContent = readResourceFileAsLines(path)
    return fileContent!!.toList("\n")
  }

}

class FileLocation {
  companion object {
    const val SG_PUBLIC_HOLIDAY_PATH = "/data/SG.txt"
    const val HK_PUBLIC_HOLIDAY_PATH = "/data/HK.txt"

  }
}