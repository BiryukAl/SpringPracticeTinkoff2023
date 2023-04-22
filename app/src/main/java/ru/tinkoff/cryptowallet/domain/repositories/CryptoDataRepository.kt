package ru.tinkoff.cryptowallet.domain.repositories

import ru.tinkoff.cryptowallet.data.cache.entities.CryptoData

interface CryptoDataRepository {
    fun getAll(): List<CryptoData>
}