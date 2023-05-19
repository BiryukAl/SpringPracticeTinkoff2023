package ru.tinkoff.cryptowallet.data.repositories

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.tinkoff.cryptowallet.data.cache.AppDatabase
import ru.tinkoff.cryptowallet.data.cache.entities.CryptoData
import ru.tinkoff.cryptowallet.data.cloud.model.CryptoCoinItem
import ru.tinkoff.cryptowallet.data.cloud.service.CoinGeckoService
import ru.tinkoff.cryptowallet.data.mappers.ResponseAndEntityMapper
import ru.tinkoff.cryptowallet.domain.repositories.CryptoDataRepository
import javax.inject.Inject


class CryptoDataRepositoryImpl @Inject constructor(
    private val localSource: AppDatabase,
    private val remoteSource: CoinGeckoService,
    private val mapper: ResponseAndEntityMapper,
) : CryptoDataRepository {
    override suspend fun getAllUses(): List<CryptoData> {
        return localSource.getCryptoDataDao().findAll()
    }

    override suspend fun getAllList(): List<CryptoCoinItem> {

        return remoteSource.getCryptoCoinsPrice(/*limitPage = 20*/).take(20)
            .map { mapper.mapCryptoPriseToCryptoCoin(it) }

//        return remoteSource.getRatesCryptoCurrency() // To much
    }

    override suspend fun updateAllUses(): List<CryptoData> {
        val localData = localSource.getCryptoDataDao().findAll()
        val idCurrencyRequest = localData.joinToString(",") { it.id }
        val remoteData = remoteSource.getCryptoCoinsPrice(idCurrency = idCurrencyRequest)

        val updateLocalData = mapper.mapCryptoPriseResponseToCryptoData(remoteData)
        updateLocalData.map { localSource.getCryptoDataDao().update(it) }

        return localSource.getCryptoDataDao().findAll()
    }

    override suspend fun addCryptoCurrency(id: String) {
        Log.d("addCryptoCurrency", "Start")
        withContext(Dispatchers.IO) {

            val remoteData = remoteSource.getCryptoCoinsPrice(idCurrency = id)
            Log.d("addCryptoCurrency: remoteData", "$remoteData")
            val newLocalData = mapper.mapCryptoPriseResponseToCryptoData(remoteData).first()
            Log.d("addCryptoCurrency: newLocalData", "$newLocalData")
            localSource.getCryptoDataDao().add(newLocalData)
        }
        Log.d("addCryptoCurrency", "End")

    }

    override suspend fun deleteCryptoCurrency(id: String): Int {
        return localSource.getCryptoDataDao().delete(id)
    }
}
