package ru.tinkoff.cryptowallet.presentation.screen.crypto.add

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ru.tinkoff.cryptowallet.domain.usecase.crypto.AddCryptoCurrencyUseCase
import ru.tinkoff.cryptowallet.domain.usecase.crypto.GetAllListCryptoUseCase
import ru.tinkoff.cryptowallet.presentation.base.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class AddCryptoViewModel @Inject constructor(
    private val addCryptoCurrencyUseCase: AddCryptoCurrencyUseCase,
    private val getAllListCryptoUseCase: GetAllListCryptoUseCase,
) : BaseViewModel() {

    private val _allCryptoCurrency: MutableLiveData<List<String>> = MutableLiveData(listOf())
    val allCryptoCurrency: LiveData<List<String>> = _allCryptoCurrency

    fun addCryptoCurrency(idCurrency: String) {
        viewModelScope.launch {
            addCryptoCurrencyUseCase(idCurrency)
        }
    }

    fun updateAllListCurrency() {
        viewModelScope.launch {
            _allCryptoCurrency.value = getAllListCryptoUseCase().map { it.id!! }
        }
    }

}
