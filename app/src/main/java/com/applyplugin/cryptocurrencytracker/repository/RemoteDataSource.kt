package com.applyplugin.cryptocurrencytracker.repository

import com.applyplugin.cryptocurrencytracker.model.CryptoResponse
import com.applyplugin.cryptocurrencytracker.util.Constants
import com.applyplugin.cryptocurrencytracker.util.Constants.Companion.API_KEY
import com.applyplugin.cryptocurrencytracker.util.Constants.Companion.PER_PAGE
import com.applypluginapp.cryptocurrency.api.CryptoAPIInterface
import retrofit2.Response
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val cryptoApiInterface: CryptoAPIInterface) {

    suspend fun getCrypto(
        id: String?,
        page: Int?,
        currency: Constants.Currency?,
        status: Constants.Status?
    ): Response<List<CryptoResponse>> {
        return cryptoApiInterface.getCryptos(
            API_KEY,
            id,
            PER_PAGE,
        )
    }

    suspend fun getAllCrypto(): Response<List<CryptoResponse>> {
        return cryptoApiInterface.getAllCrypto(
            API_KEY,
            PER_PAGE,
            "1"
        )
    }

}