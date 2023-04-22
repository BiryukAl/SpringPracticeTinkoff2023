package ru.tinkoff.cryptowallet.data.repositories

import ru.tinkoff.cryptowallet.data.cache.AppDatabase
import ru.tinkoff.cryptowallet.data.cache.entities.CryptoData
import ru.tinkoff.cryptowallet.domain.repositories.CryptoDataRepository
import javax.inject.Inject


class CryptoDataRepositoryImpl @Inject constructor(
    private val local: AppDatabase,
) : CryptoDataRepository {

    override suspend fun getAll(): List<CryptoData> {
        return local.getCryptoDataDao().findAll()
    }

}