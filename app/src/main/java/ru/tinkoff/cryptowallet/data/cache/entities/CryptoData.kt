package ru.tinkoff.cryptowallet.data.cache.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "crypto_currency")
data class CryptoData(
    @ColumnInfo("code_currency")
    @PrimaryKey
    val codeCurrency: String,
    @ColumnInfo("name_currency")
    val currencyName: String,
    @ColumnInfo("icon_link")
    val iconLink: String,
    @ColumnInfo("coast")
    val coast: Long,
)
