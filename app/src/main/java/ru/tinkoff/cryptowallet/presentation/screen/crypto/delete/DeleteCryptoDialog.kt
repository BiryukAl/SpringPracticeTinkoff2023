package ru.tinkoff.cryptowallet.presentation.screen.crypto.delete

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import ru.tinkoff.cryptowallet.R
import ru.tinkoff.cryptowallet.databinding.DialogDeleteCryptoBinding
import ru.tinkoff.cryptowallet.presentation.base.BaseDialog

@AndroidEntryPoint
class DeleteCryptoDialog : BaseDialog(R.layout.dialog_delete_crypto) {

    private val viewBinding: DialogDeleteCryptoBinding by viewBinding(DialogDeleteCryptoBinding::bind)
    private val viewModel: DeleteCryptoViewModel by viewModels()
    private val args by navArgs<DeleteCryptoDialogArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initBtn()
    }

    private fun initBtn() {
        with(viewBinding) {
            btnCancelCryptoDelete.setOnClickListener { dismiss() }
            btnDeleteCrypto.setOnClickListener {
                viewModel.deleteCrypto(args.idCrypto)
            }
        }
    }

}
