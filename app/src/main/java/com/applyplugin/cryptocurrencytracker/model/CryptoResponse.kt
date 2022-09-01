package com.applyplugin.cryptocurrencytracker.model

import com.google.gson.annotations.SerializedName
import retrofit2.Response

data class CryptoResponse(

    @SerializedName("circulating_supply")
    val circulatingSupply: String,
    @SerializedName("currency")
    val currency: String,
    @SerializedName("1d")
    val day1: priceChange,
    @SerializedName("7d")
    val day7: priceChange,
    @SerializedName("30d")
    val day30: priceChange,
    @SerializedName("365d")
    val day365: priceChange,
    @SerializedName("first_candle")
    val firstCandle: String,
    @SerializedName("first_order_book")
    val firstOrderBook: String,
    @SerializedName("first_trade")
    val firstTrade: String,
    @SerializedName("high")
    val high: String,
    @SerializedName("high_timestamp")
    val highTimestamp: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("logo_url")
    val logoUrl: String,
    @SerializedName("market_cap")
    val marketCap: String,
    @SerializedName("market_cap_dominance")
    val marketCapDominance: String,
    @SerializedName("max_supply")
    val maxSupply: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("num_exchanges")
    val numExchanges: String,
    @SerializedName("num_pairs")
    val numPairs: String,
    @SerializedName("num_pairs_unmapped")
    val numPairsUnmapped: String,
    @SerializedName("price")
    val price: String,
    @SerializedName("price_date")
    val priceDate: String,
    @SerializedName("price_timestamp")
    val priceTimestamp: String,
    @SerializedName("rank")
    val rank: String,
    @SerializedName("rank_delta")
    val rankDelta: String,
    @SerializedName("status")
    val status: String,
    @SerializedName("symbol")
    val symbol: String,
    @SerializedName("ytd")
    val ytd: priceChange

)

data class priceChange(
    @SerializedName("market_cap_change")
    val marketCapChange: String,
    @SerializedName("market_cap_change_pct")
    val marketCapChangePct: String,
    @SerializedName("price_change")
    val priceChange: String,
    @SerializedName("price_change_pct")
    val priceChangePct: String,
    @SerializedName("volume")
    val volume: String,
    @SerializedName("volume_change")
    val volumeChange: String,
    @SerializedName("volume_change_pct")
    val volumeChangePct: String
)

