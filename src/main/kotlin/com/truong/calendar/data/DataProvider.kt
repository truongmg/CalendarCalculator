package com.truong.calendar.data

interface DataProvider {

  fun loadData(): List<String>

}