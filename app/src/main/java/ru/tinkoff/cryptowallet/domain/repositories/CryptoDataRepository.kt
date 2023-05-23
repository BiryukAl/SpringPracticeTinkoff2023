package ru.tinkoff.cryptowallet.domain.repositories

import ru.tinkoff.cryptowallet.data.cache.entities.CryptoData
import ru.tinkoff.cryptowallet.data.cloud.model.CryptoCoinItem

interface CryptoDataRepository {
    suspend fun getAllUses(): List<CryptoData>

    suspend fun getAllList(): List<CryptoCoinItem>

    suspend fun updateAllUses(): List<CryptoData>

    suspend fun addCryptoCurrency(id: String)

    suspend fun deleteCryptoCurrency(id: String)
}