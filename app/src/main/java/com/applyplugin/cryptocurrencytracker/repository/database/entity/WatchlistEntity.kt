package com.applyplugin.cryptocurrencytracker.repository.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.applyplugin.cryptocurrencytracker.model.CryptoResponse
import com.applyplugin.cryptocurrencytracker.util.Constants.Companion.WATCHLIST_TABLE

@Entity(tableName = WATCHLIST_TABLE)
class WatchlistEntity (
    var crypto: CryptoResponse
){
    @PrimaryKey(autoGenerate = false)
    var id: String = crypto.id
}