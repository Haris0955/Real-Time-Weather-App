package com.weatherapp.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build

object Constants {

    const val API_KEY : String="c2835feb0620ec3d070927d71edcf65e"
    const val BASE_URL: String = "https://api.openweathermap.org/data/"
    const val METRIC_UNIT: String = "metric"
    const val PREFERENCE_NAME = "WeatherAppPreference"
    const val WEATHER_RESPONSE_DATA ="Weather_response_data"

    /**
     * This function is used check the weather the device is connected to the Internet or not.
     */
    fun isNetworkEnabled(context: Context): Boolean {
        // It answers the queries about the state of network connectivity.
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            /**
             * ?: is the Elvis operator in Kotlin. It checks if the left-hand side
             * (connectivityManager.activeNetwork) is non-null. If it's non-null, it assigns its
             * value to the network variable. If it's null, it immediately returns false.
             * **/
            // Check for an active network connection using the ConnectivityManager
                val network = connectivityManager.activeNetwork ?: return false
            // Get the network capabilities of the active network
            val activeNetwork = connectivityManager.getNetworkCapabilities(network)?: return false
            return when {
                // Check if the active network has the TRANSPORT_WIFI capability
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true

                // Check if the active network has the TRANSPORT_CELLULAR capability
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true

                // Check if the active network has the TRANSPORT_ETHERNET capability
                // Note: This is for devices that can connect via Ethernet.
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true

                // If none of the above conditions match, return false
                else -> false
            }


        }else {
            // Returns details about the currently active default data network.
            val networkInfo = connectivityManager.activeNetworkInfo
            return networkInfo != null && networkInfo.isConnectedOrConnecting
        }

    }

}