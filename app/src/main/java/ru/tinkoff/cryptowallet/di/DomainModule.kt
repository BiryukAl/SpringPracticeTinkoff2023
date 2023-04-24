package ru.tinkoff.cryptowallet.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.tinkoff.cryptowallet.data.cache.AppDatabase
import ru.tinkoff.cryptowallet.data.repositories.AssetsRepositoryImpl
import ru.tinkoff.cryptowallet.data.repositories.CryptoDataRepositoryImpl
import ru.tinkoff.cryptowallet.domain.repositories.AssetsRepository
import ru.tinkoff.cryptowallet.domain.repositories.CryptoDataRepository
import ru.tinkoff.cryptowallet.domain.usecase.AddCryptoCurrency
import ru.tinkoff.cryptowallet.domain.usecase.GetAllCurrencyUseCase
import ru.tinkoff.cryptowallet.domain.usecase.assets.AddAssetsUseCase
import ru.tinkoff.cryptowallet.domain.usecase.assets.DeleteAssetsUseCase
import ru.tinkoff.cryptowallet.domain.usecase.assets.GetAllAssetsUseCase
import ru.tinkoff.cryptowallet.domain.usecase.assets.GetOneAssetUseCase
import ru.tinkoff.cryptowallet.domain.usecase.assets.LogInAssetsUseCase
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
    fun provideAddCryptoCurrency(cryptoDataRepository: CryptoDataRepository): AddCryptoCurrency {
        return AddCryptoCurrency(cryptoDataRepository)
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
    fun provideCryptoDataRepo(local: AppDatabase): CryptoDataRepository {
        return CryptoDataRepositoryImpl(local)
    }

}
