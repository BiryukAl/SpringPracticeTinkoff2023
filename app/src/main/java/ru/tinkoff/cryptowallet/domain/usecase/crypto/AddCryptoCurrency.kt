package ru.tinkoff.cryptowallet.domain.usecase.crypto

import ru.tinkoff.cryptowallet.domain.repositories.CryptoDataRepository
import javax.inject.Inject

class AddCryptoCurrency @Inject constructor(
 val repository: CryptoDataRepository,
) {


}
