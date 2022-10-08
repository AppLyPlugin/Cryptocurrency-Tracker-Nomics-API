package com.applyplugin.cryptocurrencytracker.bindingadapter

import android.widget.ImageView
import android.widget.TextView
import androidx.compose.foundation.Image
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import androidx.navigation.findNavController
import coil.load
import coil.size.Precision
import coil.size.Scale
import coil.transform.CircleCropTransformation
import com.applyplugin.cryptocurrencytracker.R
import com.applyplugin.cryptocurrencytracker.model.CryptoResponse
import com.applyplugin.cryptocurrencytracker.ui.CryptoFragmentDirections
import com.applyplugin.cryptocurrencytracker.util.Constants.Companion.nullDateandTime
import com.applyplugin.cryptocurrencytracker.util.Constants.Companion.nullValueFloat

class CryptoRowBinding {

    companion object {

        @BindingAdapter("onCryptoClickListener")
        @JvmStatic
        fun onCryptoClickListener(cryptoRowLayout: ConstraintLayout, details: CryptoResponse) {
            cryptoRowLayout.setOnClickListener {
                try {
                    val action =
                        CryptoFragmentDirections.actionCryptoFragmentToCryptoDetails(details)
                    cryptoRowLayout.findNavController().navigate(action)
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }

        }

        @BindingAdapter("name", "symbol")
        @JvmStatic
        fun loadImageFromUrl(imageView: ImageView, name: String, symbol: String) {

            imageView
                .load(
                    "https://cryptologos.cc/logos/${
                        name.toLowerCase().replace(" ", "-")
                    }-${symbol.toLowerCase()}-logo.png?v=023"
                ) {
                    crossfade(true)
                    placeholder(R.drawable.ic_error_loading_image)
                    error(R.drawable.ic_error_loading_image)
                    transformations(CircleCropTransformation())
                    precision(Precision.EXACT)
                    scale(Scale.FILL)
                }
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
            stringChangePercentage =
                floatChangePercentage?.toString()?.format("%.4f") ?: nullValueFloat

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