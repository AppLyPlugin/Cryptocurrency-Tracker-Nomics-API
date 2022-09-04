package com.applyplugin.cryptocurrencytracker.repository.database

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.applyplugin.cryptocurrencytracker.model.CryptoResponse
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

@ProvidedTypeConverter
class CryptoTypeConverter {

    var gson = Gson()

    @TypeConverter
    fun cryptoResponseToString(cryptos : List<CryptoResponse>): String{
        return gson.toJson(cryptos)
    }

    @TypeConverter
    fun stringToCryptoResponse(data: String): List<CryptoResponse>{
        var listType = object : TypeToken<List<CryptoResponse>>(){}.type
        return gson.fromJson(data, listType)

    }
}