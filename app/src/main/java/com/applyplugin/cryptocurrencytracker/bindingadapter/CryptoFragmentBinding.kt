package com.applyplugin.cryptocurrencytracker.bindingadapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.applyplugin.cryptocurrencytracker.model.CryptoResponse
import com.applyplugin.cryptocurrencytracker.repository.database.CryptoEntity
import com.applyplugin.cryptocurrencytracker.util.NetworkResult

class CryptoFragmentBinding {

    companion object {

        @BindingAdapter("readNetworkResult", "readDatabase", requireAll = true)
        @JvmStatic
        fun erroView(
            view: View,
            networkResult: NetworkResult<List<CryptoResponse>>?,
            database: List<CryptoEntity>?
        ) {
            if (networkResult is NetworkResult.Error && database.isNullOrEmpty()) {
                view.visibility = View.VISIBLE
            } else if (networkResult is NetworkResult.Loading) {
                view.visibility = View.GONE
            } else if (networkResult is NetworkResult.Success) {
                view.visibility = View.GONE
            }else if(!database.isNullOrEmpty()){
                view.visibility = View.GONE
            }

        }
    }

}