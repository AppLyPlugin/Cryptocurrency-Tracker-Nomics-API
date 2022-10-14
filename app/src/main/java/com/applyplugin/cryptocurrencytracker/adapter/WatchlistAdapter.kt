package com.applyplugin.cryptocurrencytracker.adapter

import android.view.ActionMode
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.ViewParent
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.applyplugin.cryptocurrencytracker.MainViewModel
import com.applyplugin.cryptocurrencytracker.databinding.FragmentWatchlistBinding
import com.applyplugin.cryptocurrencytracker.databinding.WatchlistRowLayoutBinding
import com.applyplugin.cryptocurrencytracker.model.CryptoResponse
import com.applyplugin.cryptocurrencytracker.repository.database.entity.WatchlistEntity
import com.applyplugin.cryptocurrencytracker.util.CryptoDiffUtil

class WatchlistAdapter(
    private val requireActivity: FragmentActivity,
    private val mainViewModel: MainViewModel
) : RecyclerView.Adapter<WatchlistAdapter.MyViewHolder>() {

    private var cryptoWatchlist = emptyList<WatchlistEntity>()

    class MyViewHolder(private val binding: WatchlistRowLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(watchlistEntity: WatchlistEntity) {
            binding.watchlistEntity = watchlistEntity
            binding.executePendingBindings()
        }

        companion object {

            fun from(parent: ViewGroup): MyViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = WatchlistRowLayoutBinding.inflate(layoutInflater, parent, false)
                return MyViewHolder(binding)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val selectedCrypto = cryptoWatchlist[position]
        holder.bind(selectedCrypto)
    }

    override fun getItemCount(): Int {
        return cryptoWatchlist.size
    }

    fun setData(newData: List<WatchlistEntity>) {
        val cryptoDiffUtil = CryptoDiffUtil(cryptoWatchlist, newData)
        val diffUtilResult = DiffUtil.calculateDiff(cryptoDiffUtil)
        cryptoWatchlist = newData
        diffUtilResult.dispatchUpdatesTo(this)
    }

}