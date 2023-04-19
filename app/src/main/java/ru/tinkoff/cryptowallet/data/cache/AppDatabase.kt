package ru.tinkoff.cryptowallet.data.cache

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.tinkoff.cryptowallet.data.cache.dao.AssetsDao
import ru.tinkoff.cryptowallet.data.cache.dao.CryptoDataDao
import ru.tinkoff.cryptowallet.data.cache.entities.Assets
import ru.tinkoff.cryptowallet.data.cache.entities.CryptoData

@Database(entities = [CryptoData::class, Assets::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getCryptoDataDao(): CryptoDataDao
    abstract fun getAssetsDAo(): AssetsDao
}
