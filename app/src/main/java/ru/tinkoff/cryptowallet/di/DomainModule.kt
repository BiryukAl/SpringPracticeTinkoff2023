package ru.tinkoff.cryptowallet.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.tinkoff.cryptowallet.data.cache.AppDatabase
import ru.tinkoff.cryptowallet.data.cloud.service.CoinGeckoService
import ru.tinkoff.cryptowallet.data.mappers.ResponseAndEntityMapper
import ru.tinkoff.cryptowallet.data.repositories.AssetsRepositoryImpl
import ru.tinkoff.cryptowallet.data.repositories.CryptoDataRepositoryImpl
import ru.tinkoff.cryptowallet.domain.repositories.AssetsRepository
import ru.tinkoff.cryptowallet.domain.repositories.CryptoDataRepository
import ru.tinkoff.cryptowallet.domain.usecase.GetAllCurrencyUseCase
import ru.tinkoff.cryptowallet.domain.usecase.assets.AddAssetsUseCase
import ru.tinkoff.cryptowallet.domain.usecase.assets.DeleteAssetsUseCase
import ru.tinkoff.cryptowallet.domain.usecase.assets.GetAllAssetsUseCase
import ru.tinkoff.cryptowallet.domain.usecase.assets.GetOneAssetUseCase
import ru.tinkoff.cryptowallet.domain.usecase.assets.LogInAssetsUseCase
import ru.tinkoff.cryptowallet.domain.usecase.crypto.AddCryptoCurrencyUseCase
import ru.tinkoff.cryptowallet.domain.usecase.crypto.DeleteCryptoUseCase
import ru.tinkoff.cryptowallet.domain.usecase.crypto.GetAllCryptoUseCase
import ru.tinkoff.cryptowallet.domain.usecase.crypto.GetAllListCryptoUseCase
import ru.tinkoff.cryptowallet.domain.usecase.crypto.UpdateCryptoUseCase
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class DomainModule {

    @Provides
    @Singleton
    fun provideAddAssetsUseCase(assetsRepository: AssetsRepository): AddAssetsUseCase {
        return AddAssetsUseCase(assetsRepository)
    }


    @Provides
    @Singleton
    fun provideGetAllAssetsUseCase(assetsRepository: AssetsRepository): GetAllAssetsUseCase {
        return GetAllAssetsUseCase(assetsRepository)
    }

    @Provides
    @Singleton
    fun provideGetOneAssetsUseCase(assetsRepository: AssetsRepository): GetOneAssetUseCase {
        return GetOneAssetUseCase(assetsRepository)
    }

    @Provides
    @Singleton
    fun provideDeleteAssetsUseCase(assetsRepository: AssetsRepository): DeleteAssetsUseCase {
        return DeleteAssetsUseCase(assetsRepository)
    }

    @Provides
    @Singleton
    fun provideLoginAssetsUseCase(assetsRepository: AssetsRepository): LogInAssetsUseCase {
        return LogInAssetsUseCase(assetsRepository)
    }

    @Provides
    @Singleton
    fun provideGetAllCurrencyUseCase(cryptoDataRepository: CryptoDataRepository): GetAllCurrencyUseCase {
        return GetAllCurrencyUseCase(cryptoDataRepository)
    }

    @Provides
    @Singleton
    fun provideAssetsRepository(local: AppDatabase): AssetsRepository {
        return AssetsRepositoryImpl(local)
    }

    @Provides
    @Singleton
    fun provideCryptoDataRepo(
        local: AppDatabase,
        remote: CoinGeckoService,
        mapper: ResponseAndEntityMapper,
    ): CryptoDataRepository {
        return CryptoDataRepositoryImpl(local, remote, mapper)
    }


    @Provides
    @Singleton
    fun provideAddCryptoCurrencyUseCase(cryptoDataRepository: CryptoDataRepository): AddCryptoCurrencyUseCase {
        return AddCryptoCurrencyUseCase(cryptoDataRepository)
    }

    @Provides
    @Singleton
    fun provideDeleteCryptoUseCase(cryptoDataRepository: CryptoDataRepository): DeleteCryptoUseCase {
        return DeleteCryptoUseCase(cryptoDataRepository)
    }

    @Provides
    @Singleton
    fun provideGetAllCryptoUseCase(cryptoDataRepository: CryptoDataRepository): GetAllCryptoUseCase {
        return GetAllCryptoUseCase(cryptoDataRepository)
    }

    @Provides
    @Singleton
    fun provideGetAllListCryptoUseCase(cryptoDataRepository: CryptoDataRepository): GetAllListCryptoUseCase {
        return GetAllListCryptoUseCase(cryptoDataRepository)
    }

    @Provides
    @Singleton
    fun provideUpdateCryptoUseCase(cryptoDataRepository: CryptoDataRepository): UpdateCryptoUseCase {
        return UpdateCryptoUseCase(cryptoDataRepository)
    }

}
