package ru.tinkoff.cryptowallet.data.cache.dao

import androidx.room.*
import ru.tinkoff.cryptowallet.data.cache.entities.CryptoData

@Dao
interface CryptoDataDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun add(data: CryptoData): Long

    @Update
    suspend fun update(data: CryptoData)

    @Query("SELECT * FROM crypto_currency WHERE code = :code")
    suspend fun findByCode(code: String): CryptoData

    @Query("SELECT * FROM crypto_currency")
    suspend fun findAll(): List<CryptoData>

}
