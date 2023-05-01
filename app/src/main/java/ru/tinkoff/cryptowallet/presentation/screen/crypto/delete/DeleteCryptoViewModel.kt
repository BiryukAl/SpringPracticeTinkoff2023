package ru.tinkoff.cryptowallet.presentation.screen.crypto.delete

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ru.tinkoff.cryptowallet.domain.usecase.crypto.DeleteCryptoUseCase
import ru.tinkoff.cryptowallet.presentation.base.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class DeleteCryptoViewModel @Inject constructor(

    private val deleteCryptoUseCase: DeleteCryptoUseCase,
) : BaseViewModel() {

    fun deleteCrypto(id: String) {
        viewModelScope.launch {
            deleteCryptoUseCase(id)
        }
    }
}
