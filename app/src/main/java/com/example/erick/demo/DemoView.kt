package com.example.erick.demo

interface DemoView{
    fun hideText()
    fun showText(currentDate: String)
    fun showCard(event: Event)
    fun showError()
}
