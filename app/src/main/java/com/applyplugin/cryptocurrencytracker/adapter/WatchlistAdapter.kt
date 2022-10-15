package com.applyplugin.cryptocurrencytracker.adapter

import android.view.*
import androidx.fragment.app.FragmentActivity
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.applyplugin.cryptocurrencytracker.MainViewModel
import com.applyplugin.cryptocurrencytracker.R
import com.applyplugin.cryptocurrencytracker.databinding.FragmentWatchlistBinding
import com.applyplugin.cryptocurrencytracker.databinding.WatchlistRowLayoutBinding
import com.applyplugin.cryptocurrencytracker.model.CryptoResponse
import com.applyplugin.cryptocurrencytracker.repository.database.entity.WatchlistEntity
import com.applyplugin.cryptocurrencytracker.ui.WatchlistFragmentDirections
import com.applyplugin.cryptocurrencytracker.util.CryptoDiffUtil

class WatchlistAdapter(
    private val requireActivity: FragmentActivity,
    private val mainViewModel: MainViewModel
) : RecyclerView.Adapter<WatchlistAdapter.MyViewHolder>(), ActionMode.Callback {

    private var cryptoWatchlist = emptyList<WatchlistEntity>()

    class MyViewHolder(val binding: WatchlistRowLayoutBinding) :
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

        /**SINGLE CLICK LISTENER**/
        holder.binding.watchlistRowLayout.setOnClickListener{
            val action =
                WatchlistFragmentDirections.actionWatchlistFragmentToCryptoDetails(
                    selectedCrypto.crypto
                )

            holder.itemView.findNavController().navigate(action)
        }

        /**LONG PRESS LISTENER**/
        holder.binding.watchlistRowLayout.setOnLongClickListener{
            requireActivity.startActionMode(this)
            true
        }

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

    override fun onCreateActionMode(mode: ActionMode?, menu: Menu?): Boolean {
        mode?.menuInflater?.inflate(R.menu.watchlist_contextual_menu, menu)
        return true
    }

    override fun onPrepareActionMode(mode: ActionMode?, menu: Menu?): Boolean {
        return true
    }

    override fun onActionItemClicked(mode: ActionMode?, item: MenuItem?): Boolean {
        return true
    }

    override fun onDestroyActionMode(mode: ActionMode?) {

    }

}