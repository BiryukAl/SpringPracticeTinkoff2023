package ru.tinkoff.cryptowallet.presentation.screen.assets

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ru.tinkoff.cryptowallet.data.cache.entities.Assets
import ru.tinkoff.cryptowallet.domain.usecase.assets.GetAllAssetsUseCase
import ru.tinkoff.cryptowallet.domain.usecase.assets.GetOneAssetUseCase
import ru.tinkoff.cryptowallet.presentation.base.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class AssetsViewModel @Inject constructor(
    private val getAllAssetsUseCase: GetAllAssetsUseCase,
    private val getOneAsset: GetOneAssetUseCase,
) : BaseViewModel() {

    private val _assetsList: MutableLiveData<List<Assets>?> = MutableLiveData(listOf())
    val assetsList: LiveData<List<Assets>?> = _assetsList


    fun updateAssets() {
        viewModelScope.launch {
            _assetsList.value = getAllAssetsUseCase()
        }
    }

}
