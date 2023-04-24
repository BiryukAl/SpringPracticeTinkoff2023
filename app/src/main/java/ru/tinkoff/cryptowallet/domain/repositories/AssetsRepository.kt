package ru.tinkoff.cryptowallet.domain.repositories

import ru.tinkoff.cryptowallet.data.cache.entities.Assets

interface AssetsRepository {

    suspend fun getAllAssets(): List<Assets>
    suspend fun add(assets: Assets): Long
    suspend fun getById(id: Long): Assets?
    suspend fun delete(id: Long): Int
}
