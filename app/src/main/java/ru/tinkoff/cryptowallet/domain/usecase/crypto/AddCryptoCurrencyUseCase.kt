package ru.tinkoff.cryptowallet.domain.usecase.crypto

import ru.tinkoff.cryptowallet.domain.repositories.CryptoDataRepository
import javax.inject.Inject

class AddCryptoCurrencyUseCase @Inject constructor(
    private val repository: CryptoDataRepository,
) {
    suspend operator fun invoke(id: String) {
        repository.addCryptoCurrency(id)
    }
}
