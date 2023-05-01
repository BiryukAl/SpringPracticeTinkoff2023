package ru.tinkoff.cryptowallet.presentation.screen.crypto.add

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import ru.tinkoff.cryptowallet.R
import ru.tinkoff.cryptowallet.databinding.DialogAddCryptoBinding
import ru.tinkoff.cryptowallet.presentation.base.BaseDialog

@AndroidEntryPoint
class AddCryptoDialog : BaseDialog(R.layout.dialog_add_crypto) {

    private val viewBinding: DialogAddCryptoBinding by viewBinding(DialogAddCryptoBinding::bind)
    private val viewModel: AddCryptoViewModel by viewModels()
    private var spinnerAdapter: ArrayAdapter<String>? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initSpinner()
        initBtn()

    }

    private fun initSpinner() {
        val allCryptoList = viewModel.getAllListCurrency()

        val spinnerAdapter1 = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_dropdown_item,
            allCryptoList
        )
        spinnerAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerAdapter = spinnerAdapter1
        viewBinding.spAddCrypto.adapter = spinnerAdapter
    }

    private fun initBtn() {
        with(viewBinding) {
            btnCancelCryptoAdd.setOnClickListener { dismiss() }
            btnAddCryptoAdd.setOnClickListener {
                viewModel.addCryptoCurrency(spAddCrypto.selectedItem.toString())
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        spinnerAdapter = null
    }

}
