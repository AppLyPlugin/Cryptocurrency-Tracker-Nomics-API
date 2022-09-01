package com.applyplugin.cryptocurrencytracker.repository

import com.applyplugin.cryptocurrencytracker.model.CryptoResponse
import com.applyplugin.cryptocurrencytracker.util.Constants
import com.applyplugin.cryptocurrencytracker.util.Constants.Companion.API_KEY
import com.applyplugin.cryptocurrencytracker.util.Constants.Companion.PER_PAGE
import com.applypluginapp.cryptocurrency.api.CryptoAPIInterface
import retrofit2.Response
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val cryptoApiInterface: CryptoAPIInterface) {

    suspend fun getCrypto(): Response<List<CryptoResponse>> {
        return cryptoApiInterface.getAllCrypto(
            API_KEY,
            PER_PAGE,
            "1"
        )
    }

    suspend fun getCrypto(query: HashMap<String, String>) : Response<List<CryptoResponse>>{
        return cryptoApiInterface.getCryptos(query)

    }

}