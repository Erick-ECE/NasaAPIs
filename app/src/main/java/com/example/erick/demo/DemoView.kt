package com.example.erick.demo

interface DemoView{
    fun hideText()
    fun showDate(currentDate: String)
    fun showCard(event: Event)
    fun showError()
    fun showLoadingView()
    fun hideLoadingView()
}
