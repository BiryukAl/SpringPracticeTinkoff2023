package ru.tinkoff.cryptowallet.data.cloud.model.coinMarketCap


import com.google.gson.annotations.SerializedName

data class Quote(
    @SerializedName("USD")
    val usd: Usd?,
)