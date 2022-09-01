package com.applyplugin.cryptocurrencytracker.util

class Constants {

    companion object {
        val BASE_URL: String = "https://api.nomics.com"
        val API_KEY: String = "{INSERT YOUR API KEY HERE}"
        val PER_PAGE: String = "25"
        val NETWORK_TIMEOUT : Long = 10000

        //Query Keys
        const val QUERY_KEY = "key"
        const val QUERY_IDS = "ids"
        const val QUERY_INTERVAL = "interval"
        const val QUERY_CURRENCY = "quote-currency"
        const val QUERY_STATUS = "status"
        const val QUERY_PER_PAGE = "per-page"
        const val QUERY_PAGE = "page"


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