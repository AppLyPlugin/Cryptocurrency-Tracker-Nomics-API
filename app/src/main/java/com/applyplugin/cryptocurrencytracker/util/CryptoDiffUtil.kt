package com.applyplugin.cryptocurrencytracker.util

import androidx.recyclerview.widget.DiffUtil
import com.applyplugin.cryptocurrencytracker.model.CryptoResponse

class CryptoDiffUtil(
    private val oldList: List<CryptoResponse>,
    private val newList: List<CryptoResponse>
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] === newList[newItemPosition]
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }
}