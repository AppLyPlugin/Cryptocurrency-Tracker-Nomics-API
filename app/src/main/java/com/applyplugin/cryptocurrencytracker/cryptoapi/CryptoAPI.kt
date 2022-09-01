package com.applypluginapp.cryptocurrency.api

import com.applyplugin.cryptocurrencytracker.model.CryptoResponse
import com.applyplugin.cryptocurrencytracker.util.Constants
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CryptoAPIInterface {

    //Search All Crypto with Pagination
    @GET("v1/currencies/ticker")
    suspend fun getCryptos(
        @Query("key") key: String,
        @Query("ids") id: String?,
        @Query("per-page") per_page: String,
        //@Query("page") page: String?,
        //@Query("status") status: Constants.Status?,
        //@Query("quote-currency") currency: Constants.Currency?
    ): Response<List<CryptoResponse>>

    @GET("v1/currencies/ticker")
    suspend fun getAllCrypto(
        @Query("key") key: String,
        @Query("per-page") per_page: String,
        @Query("page") page: String?,
    ): Response<List<CryptoResponse>>

}