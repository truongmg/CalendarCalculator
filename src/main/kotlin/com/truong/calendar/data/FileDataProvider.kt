package com.truong.calendar.data

import com.truong.calendar.readResourceFileAsLines
import com.truong.calendar.toList

class FileDataProvider : DataProvider {

  override fun loadData(path: String): List<String> {
    val fileContent = readResourceFileAsLines(path)
    return fileContent!!.toList("\n")
  }

}