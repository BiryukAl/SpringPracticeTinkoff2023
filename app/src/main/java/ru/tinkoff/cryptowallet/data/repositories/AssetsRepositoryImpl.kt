package ru.tinkoff.cryptowallet.data.repositories

import ru.tinkoff.cryptowallet.data.cache.AppDatabase
import ru.tinkoff.cryptowallet.data.cache.entities.Assets
import ru.tinkoff.cryptowallet.domain.repositories.AssetsRepository
import javax.inject.Inject

class AssetsRepositoryImpl @Inject constructor(
    private val local: AppDatabase,
) : AssetsRepository {
    override suspend fun getAllAssets(): List<Assets> {
        return local.getAssetsDao().findAll()
    }

    override suspend fun add(assets: Assets): Long {
        return local.getAssetsDao().add(assets)
    }

    override suspend fun getById(id: Long): Assets? {
        return local.getAssetsDao().findById(id)
    }
}