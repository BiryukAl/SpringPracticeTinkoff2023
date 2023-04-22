package ru.tinkoff.cryptowallet.domain.usecase.assets

import ru.tinkoff.cryptowallet.data.cache.entities.Assets
import ru.tinkoff.cryptowallet.domain.repositories.AssetsRepository
import javax.inject.Inject

class AddAssetsUseCase @Inject constructor(
    private val assetsRepository: AssetsRepository,
) {
    suspend operator fun invoke(assets: Assets): Long {
        return assetsRepository.add(assets)
    }
}
