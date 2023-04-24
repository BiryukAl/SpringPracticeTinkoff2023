package ru.tinkoff.cryptowallet.presentation.screen.assets.add

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import ru.tinkoff.cryptowallet.R
import ru.tinkoff.cryptowallet.databinding.DialogAddAssetBinding

@AndroidEntryPoint
class AddAssetDialog : DialogFragment(R.layout.dialog_add_asset) {

    private val viewBinding: DialogAddAssetBinding by viewBinding(DialogAddAssetBinding::bind)
    private val viewModel: AddAssetViewModel by viewModels()
    private var spinnerAdapter: ArrayAdapter<String>? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initSpinner()
        initBtn()
    }

    private fun initBtn() {
        with(viewBinding) {
            btnAddAssetsCancel.setOnClickListener {
                myDismiss()
            }
            btnAddAssetsAdd.setOnClickListener {
                viewModel.addAssets(
                    addNameAssets.text.toString(),
                    spinCurrencySelection.textAlignment.toString(),
                    if (addPasswordAssets.text.toString() == "") {
                        null
                    } else {
                        addPasswordAssets.text.toString()
                    }
                )
                myDismiss()
            }
        }
    }

    private fun initSpinner() {
        val allCurrency = viewModel.getAllCurrency()

        val spinnerAdapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_dropdown_item,
            allCurrency
        )
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        viewBinding.spinCurrencySelection.adapter = spinnerAdapter
    }

    private fun myDismiss() {
        this.dismiss()
    }

    override fun onDestroy() {
        super.onDestroy()
        spinnerAdapter = null
    }

}
