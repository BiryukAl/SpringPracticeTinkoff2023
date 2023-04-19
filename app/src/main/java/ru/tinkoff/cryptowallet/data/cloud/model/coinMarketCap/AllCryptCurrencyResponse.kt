package ru.tinkoff.cryptowallet.data.cloud.model.coinMarketCap


import com.google.gson.annotations.SerializedName

data class AllCryptCurrencyResponse(
    @SerializedName("data")
    val dataCrypto: List<Data?>?,
    @SerializedName("status")
    val status: Status?,
)
