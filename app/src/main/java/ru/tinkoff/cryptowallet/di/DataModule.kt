package ru.tinkoff.cryptowallet.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.tinkoff.cryptowallet.data.cache.AppDatabase
import ru.tinkoff.cryptowallet.data.cloud.service.CoinGeckoService
import ru.tinkoff.cryptowallet.data.mappers.ResponseAndEntityMapper
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
    fun provideOkHttp(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(
                HttpLoggingInterceptor()
                    .setLevel(HttpLoggingInterceptor.Level.BODY)
            )
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit2(
        client: OkHttpClient,
        gsonConverterFactory: GsonConverterFactory,
    ): CoinGeckoService {
        return Retrofit.Builder()
            .baseUrl("https://api.coingecko.com/api/v3/")
            .client(client)
            .addConverterFactory(gsonConverterFactory)
            .build().create(CoinGeckoService::class.java)
    }

    @Provides
    @Singleton
    fun provideMapperResponseAndEntity(): ResponseAndEntityMapper {
        return ResponseAndEntityMapper()
    }

    @Provides
    @Singleton
    fun provideGsonConverter(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }
}
