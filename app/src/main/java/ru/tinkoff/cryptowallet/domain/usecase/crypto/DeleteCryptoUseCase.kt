package ru.tinkoff.cryptowallet.domain.usecase.crypto

import ru.tinkoff.cryptowallet.domain.repositories.CryptoDataRepository
import javax.inject.Inject

class DeleteCryptoUseCase @Inject constructor(
    private val repository: CryptoDataRepository,
) {

    suspend operator fun invoke(id: String): Int {
        return repository.deleteCryptoCurrency(id)
    }
}
