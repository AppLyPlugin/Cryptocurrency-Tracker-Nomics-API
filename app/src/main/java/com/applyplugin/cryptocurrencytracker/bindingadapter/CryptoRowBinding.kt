package com.applyplugin.cryptocurrencytracker.bindingadapter

import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.applyplugin.cryptocurrencytracker.R
import com.applyplugin.cryptocurrencytracker.util.Constants.Companion.nullDateandTime
import com.applyplugin.cryptocurrencytracker.util.Constants.Companion.nullValueFloat
import com.squareup.picasso.Picasso

class CryptoRowBinding {

    companion object {

        @BindingAdapter("name", "symbol")
        @JvmStatic
        fun loadImageFromUrl(imageView: ImageView, name: String, symbol: String) {

            Picasso.with(imageView.context)
                .load(
                    "https://cryptologos.cc/logos/${
                        name.toLowerCase().replace(" ", "-")
                    }-${symbol.toLowerCase()}-logo.png?v=023"
                )
                .resize(150, 150)
                .error(R.drawable.ic_error_loading_image)
                .into(imageView)

        }

        @BindingAdapter("setShortenedValue")
        @JvmStatic
        fun setShortenedValue(textView: TextView, valueComplete: String?) {

            var valueFloat = valueComplete?.toFloat()
            textView.text = valueFloat?.toString()?.format("%4f") ?: nullValueFloat

        }

        @BindingAdapter("setValuetoPercentage")
        @JvmStatic
        fun setValuetoPercentage(textView: TextView, decimalValue: String?) {

            var floatChangePercentage: Float?
            var stringChangePercentage: String?
            floatChangePercentage = decimalValue?.toFloat()?.times(100)
            stringChangePercentage = floatChangePercentage?.toString()?.format("%.4f") ?: nullValueFloat

            textView.text = stringChangePercentage + "%"

        }

        @BindingAdapter("formatDateAndTime")
        @JvmStatic
        fun formatDateAndTime(textView: TextView, dateAndTime: String?) {

//            val priceTimestamp: LocalDateTime = LocalDateTime.parse(dateAndTime)
//            val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm")
//            textView.text = formatter.format(priceTimestamp)
            textView.text = dateAndTime?.replace("T", " ")?.replace("Z", "") ?: nullDateandTime
        }

        @BindingAdapter("setTextColor")
        @JvmStatic
        fun setTextColor(view: TextView, value: String?) {

            var floatValue: Float? = value?.toFloat()

            if (floatValue != null) {
                if (floatValue > 0) {
                    view.setTextColor(ContextCompat.getColor(view.context, R.color.positive))
                } else if (floatValue < 0) {
                    view.setTextColor(ContextCompat.getColor(view.context, R.color.negative))
                } else {
                    view.setTextColor(ContextCompat.getColor(view.context, R.color.no_change))
                }
            }

        }
    }
}