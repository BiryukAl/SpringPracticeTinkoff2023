package ru.tinkoff.cryptowallet.data.mappers

import ru.tinkoff.cryptowallet.data.cache.entities.CryptoData
import ru.tinkoff.cryptowallet.data.cloud.model.CryptoCoinItem
import ru.tinkoff.cryptowallet.data.cloud.model.CryptoPriseItem
import ru.tinkoff.cryptowallet.data.cloud.model.CryptoPriseResponse

class ResponseAndEntityMapper {

    fun mapCryptoPriseResponseToCryptoData(cryptoPriseResponse: CryptoPriseResponse): List<CryptoData> {
        return cryptoPriseResponse.map { mapCryptoPriceItemToCryptoData(it) }
    }


    fun mapCryptoPriceItemToCryptoData(cryptoPriseItem: CryptoPriseItem): CryptoData {

        with(cryptoPriseItem) {
            return CryptoData(symbol!!, id!!, name!!, image!!, currentPrice!!)
        }
    }

    fun mapCryptoPriseToCryptoCoin(cryptoPriseItem: CryptoPriseItem): CryptoCoinItem {
        with(cryptoPriseItem) {
            return CryptoCoinItem(id, name, symbol)
        }
    }
}