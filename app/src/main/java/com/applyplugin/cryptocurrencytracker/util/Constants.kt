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
        const val QUERY_FILTER = "filter"
        const val QUERY_PER_PAGE = "per-page"
        const val QUERY_PAGE = "page"

        //ROOM Database
        const val DATABASE_NAME = "crypto_database"
        const val CRYPTO_TABLE = "crypto_table"

        //Filter & Data Store Preferences
        const val PREF_NAME = "crypto_preferences"
        const val FILTER_FILTER = "any"
        const val PREF_FILTER_FILTER_KEY = "filter"
        const val PREF_FILTER_FILTER_ID = "filterID"

        //Network Connectivity
        const val CONNECTIVITY = "connectivity"
    }

    enum class Currency(val currency: String){
        USD("USD"),
        EUR("EUR")
    }

    enum class Filter(val filter : String){
        all(""),
        active("active"),
        inactive("inactive"),
        dead("dead"),
        fNew("new")
    }

}