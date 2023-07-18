class CalendarCalculator {

  var holidayData = HashMap<String, List<String>>()
  var fileMap = mapOf(
    "SG" to "/data/SG.txt",
    "HK" to "/data/HK.txt"
  )

  fun loadHolidayCalendar() {
    loadDataFromFiles()
  }

  private fun loadDataFromFiles() {
    fileMap.forEach { (key, value) ->
      val fileContent = readResourceFileAsLines(value)
      assert(fileContent != null)
      holidayData[key] = fileContent!!.splitToList("\n")
    }
  }

}


