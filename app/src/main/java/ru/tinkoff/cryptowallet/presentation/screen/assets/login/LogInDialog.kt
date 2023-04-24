package ru.tinkoff.cryptowallet.presentation.screen.assets.login

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import ru.tinkoff.cryptowallet.R
import ru.tinkoff.cryptowallet.databinding.DialogLoginAssetsBinding

@AndroidEntryPoint
class LogInDialog : DialogFragment(R.layout.dialog_login_assets) {

    private val viewBinding: DialogLoginAssetsBinding by viewBinding(DialogLoginAssetsBinding::bind)
    private val viewModel: LoginViewModel by viewModels()
    private val args by navArgs<LogInDialogArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (id == null) {
            this.dismiss()
            Toast.makeText(requireContext(), "Error", Toast.LENGTH_SHORT).show()
        }
        initBtn()
    }

    private fun initBtn() {
        with(viewBinding) {
            btnLoginAssets.setOnClickListener {
                val password = viewBinding.passwordAssets.text.toString()
                val success = viewModel.login(args.idAssets, password)
                if (!success) {
                    Toast.makeText(requireContext(), "Error", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(requireContext(), "Success", Toast.LENGTH_SHORT).show()
                    myDismiss()
                }
            }
            btnDeleteAssets.setOnClickListener {
                val success = viewModel.delete(args.idAssets)
                if (success) {
                    Toast.makeText(requireContext(), "Delete", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(requireContext(), "Error", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun myDismiss() {
        this.dismiss()
    }
}
