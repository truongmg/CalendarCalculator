package com.truong.calendar.data

interface DataProvider {

  fun loadData(path: String): List<String>

}