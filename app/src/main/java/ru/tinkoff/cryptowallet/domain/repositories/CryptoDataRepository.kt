package ru.tinkoff.cryptowallet.domain.repositories

import ru.tinkoff.cryptowallet.data.cache.entities.CryptoData

interface CryptoDataRepository {
    suspend fun getAll(): List<CryptoData>
}