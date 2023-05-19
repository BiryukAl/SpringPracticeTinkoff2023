package ru.tinkoff.cryptowallet.data.cloud.service

import retrofit2.http.GET
import retrofit2.http.Query
import ru.tinkoff.cryptowallet.data.cloud.model.CryptoCoinResponse
import ru.tinkoff.cryptowallet.data.cloud.model.CryptoPriseResponse

interface CoinGeckoService {

    @GET("coins/list")
    suspend fun getRatesCryptoCurrency(
        @Query("include_platform") includePlatform: Boolean = false,
    ): CryptoCoinResponse

    @GET("coins/markets")
    suspend fun getCryptoCoinsPrice(
        @Query("vs_currency") vsCurrency: String = "usd", // example: (usd, eur, jpy, etc.)
        @Query("ids") idCurrency: String = "", // example: (bitcoin, dogecoin)
//        @Query("per_page") limitPage: Int = 20, // 1..250
        @Query("page") page: Int = 1,
    ): CryptoPriseResponse

}
