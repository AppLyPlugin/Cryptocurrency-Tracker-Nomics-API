package com.applyplugin.cryptocurrencytracker.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.compose.ui.layout.Layout
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.applyplugin.cryptocurrencytracker.databinding.CryptoRowLayoutBinding
import com.applyplugin.cryptocurrencytracker.model.CryptoResponse
import com.applyplugin.cryptocurrencytracker.util.CryptoDiffUtil

class CryptoAdapter : RecyclerView.Adapter<CryptoAdapter.MyViewHolder>() {

    private var crypto = emptyList<CryptoResponse>()

    class MyViewHolder(private val binding: CryptoRowLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(cryptoResponse: CryptoResponse) {
            binding.crypto = cryptoResponse
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): MyViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = CryptoRowLayoutBinding.inflate(layoutInflater, parent, false)
                return MyViewHolder(binding)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentResult = crypto[position]
        holder.bind(currentResult)
    }

    override fun getItemCount(): Int {
        return crypto.size
    }

    fun setData(newData: List<CryptoResponse>) {
        val cryptoDiffUtil = CryptoDiffUtil(crypto, newData)
        val diffUtilResult = DiffUtil.calculateDiff(cryptoDiffUtil)
        crypto = newData
        //notifyDataSetChanged()
        diffUtilResult.dispatchUpdatesTo(this)
    }
}