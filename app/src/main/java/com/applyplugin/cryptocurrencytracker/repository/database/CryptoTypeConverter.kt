package com.applyplugin.cryptocurrencytracker.repository.database

import com.applyplugin.cryptocurrencytracker.model.CryptoResponse
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class CryptoTypeConverter {

    var gson = Gson()

    fun cryptoResponseToString(cryptos : List<CryptoResponse>): String{
        return gson.toJson(cryptos)
    }

    fun stringToCryptoResponse(data: String): List<CryptoResponse>{
        var listType = object : TypeToken<List<CryptoResponse>>(){}.type
        return gson.fromJson(data, listType)

    }
}