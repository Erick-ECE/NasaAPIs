package com.example.erick.demo

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DemoPresenter(val view: DemoView?) {
    val API_KEY = "7BYQawZXI9owYycamnlFYK9QCFkUtg2OXE7Y87ji"

    fun fetchNasaApiData(year: Int, month: Int, day: Int) {
        view?.showLoadingView()

        val monthAux = if (month < 10) "0" + month.toString() else month.toString()
        val dayAux = if (day < 10) "0" + day.toString() else day.toString()
        val date = "$year-$monthAux-$dayAux"

        view?.showDate(date)

        NasaClient.service.getData(API_KEY, date).enqueue(object : Callback<Event> {
            override fun onFailure(call: Call<Event>, t: Throwable) {
                view?.hideLoadingView()
                view?.showError()
            }

            override fun onResponse(call: Call<Event>, response: Response<Event>) {
                view?.hideLoadingView()
                view?.showCard(response.body()!!)
            }

        })
    }
}