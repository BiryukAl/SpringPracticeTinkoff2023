package ru.tinkoff.cryptowallet.data.cloud.model


import com.google.gson.annotations.SerializedName

data class CryptoCoinItem(
    @SerializedName("id")
    val id: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("symbol")
    val code: String?,
)