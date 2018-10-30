package com.example.erick.demo

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


object NasaClient{
    val service: NasaService

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.nasa.gov/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        service = retrofit.create(NasaService::class.java)
    }
}

interface NasaService{
    @GET("planetary/apod")
    fun getData(@Query("api_key") apiKey: String, @Query("date") date: String): Call<Event>
}