package ru.tinkoff.cryptowallet.presentation.screen.assets.add

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import ru.tinkoff.cryptowallet.R
import ru.tinkoff.cryptowallet.databinding.DialogAddAssetBinding
import ru.tinkoff.cryptowallet.presentation.base.BaseDialog

@AndroidEntryPoint
class AddAssetDialog : BaseDialog(R.layout.dialog_add_asset) {

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
                dismiss()
            }
            btnAddAssetsAdd.setOnClickListener {
                viewModel.addAssets(
                    addNameAssets.text.toString(),
                    spinCurrencySelection.selectedItem.toString(),
                    if (addPasswordAssets.text.toString() == "") {
                        null
                    } else {
                        addPasswordAssets.text.toString()
                    }
                )
                dismiss()
            }
        }
    }

    private fun initSpinner() {
        viewModel.updateAllCurrency()
        viewModel.allCurrency.observe(viewLifecycleOwner) { currency ->
            val newSpinnerAdapter = ArrayAdapter(
                requireContext(),
                android.R.layout.simple_spinner_dropdown_item,
                currency!!
            )
            newSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinnerAdapter = newSpinnerAdapter
            viewBinding.spinCurrencySelection.adapter = spinnerAdapter
        }


    }


    override fun onDestroy() {
        super.onDestroy()
        spinnerAdapter = null
    }

}
