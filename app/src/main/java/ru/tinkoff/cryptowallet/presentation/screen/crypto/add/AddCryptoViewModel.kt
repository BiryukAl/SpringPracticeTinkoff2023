package ru.tinkoff.cryptowallet.presentation.screen.crypto.add

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ru.tinkoff.cryptowallet.data.cloud.model.CryptoCoinItem
import ru.tinkoff.cryptowallet.domain.usecase.crypto.AddCryptoCurrencyUseCase
import ru.tinkoff.cryptowallet.domain.usecase.crypto.GetAllListCryptoUseCase
import ru.tinkoff.cryptowallet.presentation.base.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class AddCryptoViewModel @Inject constructor(
    private val addCryptoCurrencyUseCase: AddCryptoCurrencyUseCase,
    private val getAllListCryptoUseCase: GetAllListCryptoUseCase,
) : BaseViewModel() {

    fun addCryptoCurrency(idCurrency: String) {
        viewModelScope.launch {
            addCryptoCurrencyUseCase(idCurrency)
        }
    }

    fun getAllListCurrency(): List<String> {
        var list: List<CryptoCoinItem> = listOf()

        viewModelScope.launch {
            list = getAllListCryptoUseCase()
        }

        return list.map { it.id!! }

    }

}
