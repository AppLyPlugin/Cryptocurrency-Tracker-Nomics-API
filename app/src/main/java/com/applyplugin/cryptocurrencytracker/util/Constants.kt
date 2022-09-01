package com.applyplugin.cryptocurrencytracker.util

class Constants {

    companion object {
        val BASE_URL: String = "https://api.nomics.com"
        val API_KEY: String = "{INSERT YOUR API KEY HERE}"
        val PER_PAGE: String = "25"
        val NETWORK_TIMEOUT : Long = 10000

    }

    enum class Currency(val currency: String){
        USD("USD"),
        EUR("EUR")
    }

    enum class Status(val status : String){
        active("active"),
        inactive("inactive"),
        dead("dead")
    }

}