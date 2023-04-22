package ru.tinkoff.cryptowallet.presentation.screen.assets.add

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ru.tinkoff.cryptowallet.data.cache.entities.Assets
import ru.tinkoff.cryptowallet.domain.usecase.GetAllCurrencyUseCase
import ru.tinkoff.cryptowallet.domain.usecase.assets.AddAssetsUseCase
import ru.tinkoff.cryptowallet.presentation.base.BaseViewModel
import javax.inject.Inject


@HiltViewModel
class AddAssetViewModel @Inject constructor(
    private val addAssetsUseCase: AddAssetsUseCase,
    private val getCurrency: GetAllCurrencyUseCase,
) : BaseViewModel() {
    fun addAssets(name: String, currency: String, password: String?) {
        val assets = Assets(null, name, currency, password)
        viewModelScope.launch {
            addAssetsUseCase(assets)
        }
    }

    fun getAllCurrency(): List<String> {
        var allCurrency: List<String>? = null

        viewModelScope.launch {
            allCurrency = getCurrency()
        }

        return allCurrency ?: listOf()
    }
}
