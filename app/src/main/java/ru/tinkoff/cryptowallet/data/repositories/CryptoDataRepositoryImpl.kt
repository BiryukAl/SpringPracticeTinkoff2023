package ru.tinkoff.cryptowallet.data.repositories

import ru.tinkoff.cryptowallet.data.cache.AppDatabase
import ru.tinkoff.cryptowallet.data.cloud.service.CoinLayerService
import ru.tinkoff.cryptowallet.domain.repositories.CryptoDataRepository
import javax.inject.Inject


class CryptoDataRepositoryImpl @Inject constructor(
    private val local: AppDatabase,
    private val remote: CoinLayerService,
) : CryptoDataRepository {


}