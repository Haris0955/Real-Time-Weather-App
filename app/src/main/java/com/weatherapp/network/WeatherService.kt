package com.weatherapp.network


import com.weatherapp.models.WeatherResponse


import retrofit.Call
import retrofit.http.GET
import retrofit.http.Query

/**
 * An Interface which defines the HTTP operations Functions.
 */
interface WeatherService {

    @GET("2.5/weather")
    fun getWeatherResponse(
        @Query("lat") lat: Double?,
        @Query("lon") lon: Double?,
        @Query("appid") appid : String?,
        @Query("units") units : String?
    ): Call<WeatherResponse>
}