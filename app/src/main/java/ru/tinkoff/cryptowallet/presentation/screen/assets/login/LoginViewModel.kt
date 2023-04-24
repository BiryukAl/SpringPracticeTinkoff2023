package ru.tinkoff.cryptowallet.presentation.screen.assets.login

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ru.tinkoff.cryptowallet.domain.usecase.assets.DeleteAssetsUseCase
import ru.tinkoff.cryptowallet.domain.usecase.assets.LogInAssetsUseCase
import ru.tinkoff.cryptowallet.presentation.base.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val logInAssetsUseCase: LogInAssetsUseCase,
    private val deleteAssetsUseCase: DeleteAssetsUseCase,
) : BaseViewModel() {

    fun login(id: Long, password: String): Boolean {
        var success = false
        viewModelScope.launch {
            val assets = logInAssetsUseCase(id, password)
            success = assets != null
        }
        return success
    }

    fun delete(id: Long): Boolean {
        var success = false
        viewModelScope.launch {
            success = deleteAssetsUseCase(id)
        }
        return success
    }
}
