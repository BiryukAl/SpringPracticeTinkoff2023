package ru.tinkoff.cryptowallet.data.cloud.model


import com.google.gson.annotations.SerializedName

data class CryptoPriseItem(
    @SerializedName("current_price")
    val currentPrice: Double?,
    @SerializedName("id")
    val id: String?,
    @SerializedName("image")
    val image: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("symbol")
    val symbol: String?,
)