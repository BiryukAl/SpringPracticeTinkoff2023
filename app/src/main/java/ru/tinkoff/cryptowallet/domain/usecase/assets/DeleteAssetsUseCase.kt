package ru.tinkoff.cryptowallet.domain.usecase.assets

import ru.tinkoff.cryptowallet.domain.repositories.AssetsRepository
import javax.inject.Inject

class DeleteAssetsUseCase @Inject constructor(
    private val assetsRepository: AssetsRepository,
) {
    suspend operator fun invoke(id: Long): Boolean {
        val success = assetsRepository.delete(id)
        return success != 0
    }
}
