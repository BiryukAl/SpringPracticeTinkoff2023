package ru.tinkoff.cryptowallet.domain.usecase.crypto

import ru.tinkoff.cryptowallet.data.cache.entities.CryptoData
import ru.tinkoff.cryptowallet.domain.repositories.CryptoDataRepository
import javax.inject.Inject

class GetAllCryptoUseCase @Inject constructor(
    private val cryptoDataRepository: CryptoDataRepository,
) {

    suspend operator fun invoke(): List<CryptoData> {
        return cryptoDataRepository.getAll()
    }

}
