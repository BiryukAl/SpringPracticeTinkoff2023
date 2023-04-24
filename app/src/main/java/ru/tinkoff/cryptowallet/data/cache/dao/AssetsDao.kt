package ru.tinkoff.cryptowallet.data.cache.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import ru.tinkoff.cryptowallet.data.cache.entities.Assets

@Dao
interface AssetsDao {

    @Insert
    suspend fun add(assets: Assets): Long

    @Update
    suspend fun update(assets: Assets)

    @Query("SELECT * FROM assets WHERE id = :id")
    suspend fun findById(id: Long): Assets?

    @Query("SELECT * FROM assets")
    suspend fun findAll(): List<Assets>

    @Query("DELETE FROM assets WHERE id = :id")
    suspend fun delete(id: Long): Int
}
