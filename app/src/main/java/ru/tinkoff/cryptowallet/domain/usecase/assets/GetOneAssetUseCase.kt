package ru.tinkoff.cryptowallet.domain.usecase.assets

import ru.tinkoff.cryptowallet.data.cache.entities.Assets
import ru.tinkoff.cryptowallet.domain.repositories.AssetsRepository
import javax.inject.Inject

class GetOneAssetUseCase @Inject constructor(
    private val assetsRepository: AssetsRepository,
) {
    suspend operator fun invoke(id: Long): Assets? {
        return assetsRepository.getById(id)
    }
}
