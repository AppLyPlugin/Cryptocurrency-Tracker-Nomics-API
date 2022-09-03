package com.applypluginapp.cryptocurrency.api

import com.applyplugin.cryptocurrencytracker.model.CryptoResponse
import com.applyplugin.cryptocurrencytracker.util.Constants
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface CryptoAPIInterface {

    //Search All Crypto with Pagination
    @GET("v1/currencies/ticker")
    suspend fun getCryptos(
        @QueryMap cryptoQuery: HashMap<String, String>
    ): Response<List<CryptoResponse>>

    @GET("v1/currencies/ticker")
    suspend fun getAllCrypto(
        @Query("key") key: String,
        @Query("per-page") per_page: String,
        @Query("page") page: String?,
    ): Response<List<CryptoResponse>>

}