package com.applyplugin.cryptocurrencytracker.repository

import com.applyplugin.cryptocurrencytracker.repository.database.CryptoDao
import com.applyplugin.cryptocurrencytracker.repository.database.entity.CryptoEntity
import com.applyplugin.cryptocurrencytracker.repository.database.entity.WatchlistEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalDataSource @Inject constructor(
    private val cryptoDao: CryptoDao
) {

    fun readCryptos(): Flow<List<CryptoEntity>> {
        return cryptoDao.readCryptos()
    }

    fun readCryptoWatchlist(): Flow<List<WatchlistEntity>>{
        return  cryptoDao.readWatchlist()
    }

    suspend fun insertCryptos(cryptoEntity: CryptoEntity){
        cryptoDao.insertCryptos(cryptoEntity)
    }

    suspend fun insertCryptoToWatchlist(watchlistEntity: WatchlistEntity){
        cryptoDao.insertCryptoToWatchlist(watchlistEntity)
    }

    suspend fun deleteCryptoFromWatchlist(watchlistEntity: WatchlistEntity){
        cryptoDao.deleteFromWatchlist(watchlistEntity)
    }

    suspend fun deleteWatchlist(){
        cryptoDao.deleteWatchlist()
    }

}