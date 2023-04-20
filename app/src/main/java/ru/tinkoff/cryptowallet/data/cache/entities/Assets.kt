package ru.tinkoff.cryptowallet.data.cache.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "assets")
data class Assets(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo("id")
    val id: Long?,
    @ColumnInfo("name")
    val name: String,
    @ColumnInfo("currency")
    val currency: String,
    @ColumnInfo("password")
    val password: String?,
)
