package com.applyplugin.cryptocurrencytracker.repository

import com.applyplugin.cryptocurrencytracker.repository.database.CryptoDao
import com.applyplugin.cryptocurrencytracker.repository.database.entity.CryptoEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalDataSource @Inject constructor(
    private val cryptoDao: CryptoDao
) {

    fun readDatabase(): Flow<List<CryptoEntity>> {
        return cryptoDao.readCryptos()
    }

    suspend fun insertCryptos(cryptoEntity: CryptoEntity){
        cryptoDao.insertCryptos(cryptoEntity)
    }

}