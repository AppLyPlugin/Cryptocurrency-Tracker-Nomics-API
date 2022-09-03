package com.applyplugin.cryptocurrencytracker.repository.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface CryptoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCryptos(cryptoEntity: CryptoEntity)

    @Query("SELECT * FROM crypto_table ORDER BY id ASC")
    fun readCryptos(): Flow<List<CryptoEntity>>

}