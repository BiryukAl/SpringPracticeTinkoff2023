package ru.tinkoff.cryptowallet.presentation.screen.crypto

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ru.tinkoff.cryptowallet.data.cache.entities.CryptoData
import ru.tinkoff.cryptowallet.domain.usecase.crypto.GetAllCryptoUseCase
import ru.tinkoff.cryptowallet.presentation.base.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class CryptoViewModel @Inject constructor(
    private val getAllCryptoUseCase: GetAllCryptoUseCase,
) : BaseViewModel() {

    private val _cryptoList: MutableLiveData<List<CryptoData>?> = MutableLiveData(listOf())
    val cryptoList: LiveData<List<CryptoData>?> = _cryptoList


    fun updateCryptoData() {
        viewModelScope.launch {
            _cryptoList.value = getAllCryptoUseCase()
        }
    }


}
