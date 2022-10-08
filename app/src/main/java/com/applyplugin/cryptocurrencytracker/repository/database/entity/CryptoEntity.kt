package com.applyplugin.cryptocurrencytracker.repository.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.applyplugin.cryptocurrencytracker.model.CryptoResponse
import com.applyplugin.cryptocurrencytracker.util.Constants.Companion.CRYPTO_TABLE

@Entity(tableName = CRYPTO_TABLE)
class CryptoEntity(
    var crypto: List<CryptoResponse>
) {
    @PrimaryKey(autoGenerate = false)
    var id: Int = 0
}