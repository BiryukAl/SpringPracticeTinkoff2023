package ru.tinkoff.cryptowallet.data.cloud.service

import retrofit2.http.GET
import retrofit2.http.Query
import ru.tinkoff.cryptowallet.data.cloud.model.CryptoCurrencyResponse

interface CoinLayerService {

    @GET("live")
    suspend fun getRatesCryptoCurrency(
        @Query("target") target: String = "USD",
    ): CryptoCurrencyResponse

}
