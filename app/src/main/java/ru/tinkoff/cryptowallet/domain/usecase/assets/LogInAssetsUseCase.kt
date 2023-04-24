package ru.tinkoff.cryptowallet.domain.usecase.assets

import ru.tinkoff.cryptowallet.data.cache.entities.Assets
import ru.tinkoff.cryptowallet.domain.repositories.AssetsRepository
import javax.inject.Inject

class LogInAssetsUseCase @Inject constructor(
    private val assetsRepository: AssetsRepository,
) {

    suspend operator fun invoke(id: Long, password: String): Assets? {
        val assets = assetsRepository.getById(id)

        if (assets?.password == password) {


            // TODO: Add id into shared preference
            return assets
        }
        return null
    }
}
