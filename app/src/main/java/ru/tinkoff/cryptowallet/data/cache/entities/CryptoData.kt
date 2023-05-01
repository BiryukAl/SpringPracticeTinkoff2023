package ru.tinkoff.cryptowallet.data.cache.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "crypto_currency")
data class CryptoData(
    @ColumnInfo("code")
    @PrimaryKey
    val code: String,
    @ColumnInfo("id")
    val id: String,
    @ColumnInfo("name")
    val name: String,
    @ColumnInfo("icon_link")
    val iconLink: String,
    @ColumnInfo("coast")
    val coast: Double,
)
