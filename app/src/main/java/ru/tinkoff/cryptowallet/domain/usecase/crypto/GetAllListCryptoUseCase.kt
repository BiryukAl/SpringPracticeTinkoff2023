package ru.tinkoff.cryptowallet.domain.usecase.crypto

import ru.tinkoff.cryptowallet.data.cloud.model.CryptoCoinItem
import ru.tinkoff.cryptowallet.domain.repositories.CryptoDataRepository
import javax.inject.Inject

class GetAllListCryptoUseCase @Inject constructor(
    private val repository: CryptoDataRepository,
) {

    suspend operator fun invoke(): List<CryptoCoinItem> {
        return repository.getAllList()
    }
}
