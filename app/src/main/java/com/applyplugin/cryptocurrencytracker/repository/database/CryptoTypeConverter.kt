package com.applyplugin.cryptocurrencytracker.repository.database

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.applyplugin.cryptocurrencytracker.model.CryptoResponse
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

@ProvidedTypeConverter
class CryptoTypeConverter {

    var gson = Gson()

    @TypeConverter
    fun listOfCryptoResponseToString(cryptos : List<CryptoResponse>): String{
        return gson.toJson(cryptos)
    }

    @TypeConverter
    fun stringToListofCryptoResponse(data: String): List<CryptoResponse>{
        var listType = object : TypeToken<List<CryptoResponse>>(){}.type
        return gson.fromJson(data, listType)

    }

    @TypeConverter
    fun cryptoResponsetoString(crypto: CryptoResponse) : String{
        return gson.toJson(crypto)

    }

    @TypeConverter
    fun stringToCryptoResponse(data: String): CryptoResponse{
        val listType = object : TypeToken<CryptoResponse>(){}.type
        return gson.fromJson(data, listType)

    }
}