package com.applyplugin.cryptocurrencytracker.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.applyplugin.cryptocurrencytracker.util.Constants.Companion.API_KEY
import com.applyplugin.cryptocurrencytracker.util.Constants.Companion.PER_PAGE
import com.applyplugin.cryptocurrencytracker.util.Constants.Companion.QUERY_CURRENCY
import com.applyplugin.cryptocurrencytracker.util.Constants.Companion.QUERY_IDS
import com.applyplugin.cryptocurrencytracker.util.Constants.Companion.QUERY_INTERVAL
import com.applyplugin.cryptocurrencytracker.util.Constants.Companion.QUERY_KEY
import com.applyplugin.cryptocurrencytracker.util.Constants.Companion.QUERY_PAGE
import com.applyplugin.cryptocurrencytracker.util.Constants.Companion.QUERY_PER_PAGE
import com.applyplugin.cryptocurrencytracker.util.Constants.Companion.QUERY_STATUS

class CryptoViewModel(application: Application) : AndroidViewModel(application) {

    fun applyQuery(): HashMap<String, String> {

        val queries : HashMap<String, String> = HashMap()

        queries[QUERY_KEY] = API_KEY
        queries[QUERY_IDS] = "BTC"
        queries[QUERY_CURRENCY] = ""
        queries[QUERY_INTERVAL] = ""
        queries[QUERY_STATUS] = ""
        queries[QUERY_PER_PAGE] = PER_PAGE
        queries[QUERY_PAGE] = "1"

        while(queries.values.removeIf{it == ""}){}

        return queries
    }
}