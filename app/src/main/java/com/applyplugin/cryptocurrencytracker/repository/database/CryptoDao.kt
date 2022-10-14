package com.applyplugin.cryptocurrencytracker.repository.database

import androidx.room.*
import com.applyplugin.cryptocurrencytracker.repository.database.entity.CryptoEntity
import com.applyplugin.cryptocurrencytracker.repository.database.entity.WatchlistEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CryptoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCryptos(cryptoEntity: CryptoEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCryptoToWatchlist(watchlistEntity: WatchlistEntity)

    @Query("SELECT * FROM crypto_table ORDER BY id ASC")
    fun readCryptos(): Flow<List<CryptoEntity>>

    @Query("SELECT * FROM watchlist_table ORDER BY id ASC")
    fun readWatchlist(): Flow<List<WatchlistEntity>>

    @Delete
    suspend fun deleteFromWatchlist(watchlistEntity: WatchlistEntity)

    @Query("DELETE FROM watchlist_table")
    suspend fun deleteWatchlist()

}