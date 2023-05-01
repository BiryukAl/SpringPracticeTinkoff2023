package ru.tinkoff.cryptowallet.domain.usecase

import ru.tinkoff.cryptowallet.domain.repositories.CryptoDataRepository
import javax.inject.Inject

class GetAllCurrencyUseCase @Inject constructor(
    private val cryptoDataRepository: CryptoDataRepository,
) {
    suspend operator fun invoke(): List<String> {
        val list = cryptoDataRepository.getAllUses().map { it.code }
        val list2: MutableList<String> = list as MutableList<String>
        list2.add("USD")
        list2.add("RUB")
        return list2
    }
}
