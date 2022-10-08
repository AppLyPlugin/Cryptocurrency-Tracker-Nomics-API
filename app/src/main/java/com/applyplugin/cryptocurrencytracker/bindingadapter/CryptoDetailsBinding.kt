package com.applyplugin.cryptocurrencytracker.bindingadapter

import okhttp3.internal.format
import retrofit2.http.Url
import java.text.DecimalFormat

class CryptoDetailsBinding {

    companion object {

        fun formatDate(timeStamp: String): String {

            var formattedTimeStamp: String
            formattedTimeStamp = timeStamp.substringBefore("T", "")
            return formattedTimeStamp
        }

        fun formatTime(timeStamp: String): String{

            var formattedTimeStamp: String
            formattedTimeStamp = timeStamp.substringAfter("T", "")
                        .substringBefore("Z", "");

            return formattedTimeStamp

        }

        fun formatRound2Decimals(value: String): String{

            var formattedValue: String
            var formatValue = DecimalFormat("#.##")
            formattedValue = formatValue.format(value.toFloat()).toString()

            return formattedValue
        }

        fun formatToPercentage(value: String): String{

            var formattedValue : String
            var fValue : Float

            fValue = value.toFloat()
            fValue *= 100

            formattedValue = formatRound2Decimals(fValue.toString()) ?: "0"
            return formattedValue + "%"

        }
    }

}