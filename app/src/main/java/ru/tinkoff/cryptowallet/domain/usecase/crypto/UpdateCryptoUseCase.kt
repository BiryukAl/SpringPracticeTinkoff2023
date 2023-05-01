package ru.tinkoff.cryptowallet.domain.usecase.crypto

import ru.tinkoff.cryptowallet.data.cache.entities.CryptoData
import ru.tinkoff.cryptowallet.domain.repositories.CryptoDataRepository
import javax.inject.Inject

class UpdateCryptoUseCase @Inject constructor(
    private val repository: CryptoDataRepository,
) {

    suspend operator fun invoke(): List<CryptoData> {
        return repository.updateAllUses()
    }
}
