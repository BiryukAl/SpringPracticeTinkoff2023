package ru.tinkoff.cryptowallet.presentation.screen.crypto

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import ru.tinkoff.cryptowallet.R
import ru.tinkoff.cryptowallet.databinding.FragmentCryptoBinding
import ru.tinkoff.cryptowallet.presentation.adapters.CryptoAdapter
import ru.tinkoff.cryptowallet.presentation.base.BaseFragment

@AndroidEntryPoint
class CryptoFragment : BaseFragment(R.layout.fragment_crypto) {

    private val viewBinding: FragmentCryptoBinding by viewBinding(FragmentCryptoBinding::bind)
    private val viewModel: CryptoViewModel by viewModels()
    private var cryptoAdapter: CryptoAdapter? = null


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerViewItems()
        initBtnShowAddDialog()
    }

    private fun initBtnShowAddDialog() {
        viewBinding.btnAddCrypto.setOnClickListener {
            findNavController().navigate(R.id.action_cryptoFragment_to_addCryptoDialog)
        }
    }

    private fun initRecyclerViewItems() {
        cryptoAdapter = CryptoAdapter().apply {
            onItemClickListener = ::onItemClicked
        }
        updateRecyclerViewItems()
        val manager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        viewBinding.listCrypto.apply {
            adapter = cryptoAdapter
            layoutManager = manager
        }

    }

    private fun onItemClicked(itemPosition: Int) {
        viewModel.cryptoList.observe(viewLifecycleOwner) { crypto ->
            val action =
                CryptoFragmentDirections.actionCryptoFragmentToDeleteCryptoDialog(crypto!![itemPosition].id)
            findNavController().navigate(action)
        }
    }

    private fun updateRecyclerViewItems() {
        viewModel.getCryptoData()
        if (cryptoAdapter != null) {
            cryptoAdapter.apply {
                viewModel.cryptoList.observe(viewLifecycleOwner) { cryptoData ->
                    if (cryptoData != null) {
                        this?.listItems = cryptoData
                    }
                }
            }
        }
    }


}
