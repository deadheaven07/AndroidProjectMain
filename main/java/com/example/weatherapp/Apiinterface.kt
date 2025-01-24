package com.example.weatherapp

import android.telecom.Call

interface Apiinterface {
    @GET("weather")
    fun getWeatherData(
        @Querry("q") city:String,
        @Querry("apiid") appid:String,
        @Querry("units") unit:String
        ) : Call<Weather>
}