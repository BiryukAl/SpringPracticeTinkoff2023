package ru.tinkoff.cryptowallet.domain.repositories

import ru.tinkoff.cryptowallet.data.cache.entities.Assets

interface AssetsRepository {

    suspend fun getAllAssets(): List<Assets>
    suspend fun add(assets: Assets)
    suspend fun getById(id: Long): Assets?
}