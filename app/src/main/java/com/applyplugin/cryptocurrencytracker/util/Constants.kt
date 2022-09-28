package com.applyplugin.cryptocurrencytracker.util

class Constants {

    companion object {
        const val BASE_URL: String = "https://api.nomics.com"
        const val API_KEY: String = "{INSERT YOUR API KEY HERE}"
        const val PER_PAGE: String = "100"
        const val REFRESH_CRYPTO : Long = 60000
        val NETWORK_TIMEOUT: Long = 10000

        //contants for required null values
        const val nullValueInt = "0"
        const val nullValueFloat = "0.00"
        const val nullDateandTime = "0000-00-00 00:00:00"

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

    enum class Currency(val currency: String) {
        USD("USD"),
        EUR("EUR")
    }

    enum class Filter(val filter: String) {
        all(""),
        active("active"),
        inactive("inactive"),
        dead("dead"),
        fNew("new")
    }

}