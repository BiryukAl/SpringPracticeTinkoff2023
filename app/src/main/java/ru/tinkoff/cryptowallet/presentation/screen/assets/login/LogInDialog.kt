package ru.tinkoff.cryptowallet.presentation.screen.assets.login

import android.app.Dialog
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import ru.tinkoff.cryptowallet.R
import ru.tinkoff.cryptowallet.databinding.DialogLoginAssetsBinding
import ru.tinkoff.cryptowallet.presentation.base.BaseDialog

@AndroidEntryPoint
class LogInDialog : BaseDialog(R.layout.dialog_login_assets) {

    private val viewBinding: DialogLoginAssetsBinding by viewBinding(DialogLoginAssetsBinding::bind)
    private val viewModel: LoginViewModel by viewModels()
    private val args by navArgs<LogInDialogArgs>()

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        /*https://stackoverflow.com/questions/63007406/dialogfragment-triggered-but-not-showing-the-layout-in-navigation-architecture*/
        return this.activity?.let {
            val builder = AlertDialog.Builder(it)
            val inflater = requireActivity().layoutInflater

            val layoutId: Int = R.layout.dialog_login_assets

            val rootView = inflater.inflate(layoutId, null)
            builder.setView(rootView)

            val alertDialog = builder.create()
            alertDialog
        } ?: throw IllegalStateException("Activity cannot be null")
    }

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
                    dismiss()
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


}
