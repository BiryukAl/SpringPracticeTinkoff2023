package ru.tinkoff.cryptowallet.di

import android.content.Context
import androidx.room.Room
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.tinkoff.cryptowallet.data.cache.AppDatabase
import ru.tinkoff.cryptowallet.data.cloud.model.CryptoCurrencyResponse
import ru.tinkoff.cryptowallet.data.cloud.model.RatesDeserializer
import ru.tinkoff.cryptowallet.data.cloud.service.CoinLayerService
import ru.tinkoff.cryptowallet.data.cloud.service.CoinMarketCapService
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Provides
    @Singleton
    fun provideAppDatabase(
        @ApplicationContext
        context: Context,
    ): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "crypto_wallet"
        ).build()
    }

    @Provides
    @Singleton
    @RetrofitCoinLayer
    fun provideRetrofit2ForCoinLayer(
        client: OkHttpClient,
        gsonConverterFactory: GsonConverterFactory,
    ): CoinLayerService {
        return Retrofit.Builder()
            .baseUrl("http://api.coinlayer.com/")
            .client(client)
            .addConverterFactory(gsonConverterFactory)
            .build().create(CoinLayerService::class.java)
    }

    @Provides
    @Singleton
    @RetrofitCoinLayer
    fun provideOkHttpForCoinLayer(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor { chain ->
                val modifiedUrl = chain.request().url.newBuilder()
                    .addQueryParameter("access_key", "519c10da79cf29c1fbb41264c26aa3b9")
                    .build()
                val request = chain.request().newBuilder().url(modifiedUrl).build()
                chain.proceed(request)
            }
            .build()
    }

    @Provides
    @Singleton
    @RetrofitCoinMarketCap
    fun provideRetrofit2ForCoinMarketCap(
        client: OkHttpClient,
        gsonConverterFactory: GsonConverterFactory,
    ): CoinMarketCapService {
        return Retrofit.Builder()
            .baseUrl("https://pro-api.coinmarketcap.com/")
            .client(client)
            .addConverterFactory(gsonConverterFactory)
            .build().create(CoinMarketCapService::class.java)
    }


    @Provides
    @Singleton
    @RetrofitCoinMarketCap
    fun provideOkHttpForCoinMarketCap(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor { chain ->
                val modifiedUrl = chain.request().url.newBuilder()
                    .addQueryParameter("CMC_PRO_API_KEY", "989b956d-d398-4a5d-9cc7-b4c4194429ff")
                    .build()
                val request = chain.request().newBuilder().url(modifiedUrl).build()
                chain.proceed(request)
            }
            .build()
    }

    @Provides
    @Singleton
    fun provideGsonConverter(): GsonConverterFactory {
        return GsonConverterFactory.create(
            GsonBuilder()
                .registerTypeAdapter(CryptoCurrencyResponse::class.java, RatesDeserializer())
                .create()
        )
    }

}

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class RetrofitCoinLayer

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class RetrofitCoinMarketCap
