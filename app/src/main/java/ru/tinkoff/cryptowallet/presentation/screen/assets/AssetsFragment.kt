package ru.tinkoff.cryptowallet.presentation.screen.assets

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import ru.tinkoff.cryptowallet.R
import ru.tinkoff.cryptowallet.databinding.FragmentAssetsBinding
import ru.tinkoff.cryptowallet.presentation.adapters.AssetsAdapter
import ru.tinkoff.cryptowallet.presentation.base.BaseFragment

@AndroidEntryPoint
class AssetsFragment : BaseFragment(R.layout.fragment_assets) {

    private val viewBinding: FragmentAssetsBinding by viewBinding(FragmentAssetsBinding::bind)
    private val viewModel: AssetsViewModel by viewModels()
    private var assetsAdapter: AssetsAdapter? = null

    override fun onResume() {
        super.onResume()
        updateRecyclerViewItems()

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        initShowAddDialog()
    }

    private fun initShowAddDialog() {
        viewBinding.btnAddAssets.setOnClickListener {
            findNavController().navigate(R.id.action_assetsFragment_to_addAssetDialog)
        }
    }

    private fun updateRecyclerViewItems() {
        viewModel.updateAssets()
        if (assetsAdapter != null) {
            assetsAdapter.apply {
                viewModel.assetsList.observe(viewLifecycleOwner) { assets ->
                    if (assets != null) {
                        this?.listAssets = assets
                    }
                }
            }
        }
    }

    private fun initRecyclerView() {
        assetsAdapter = AssetsAdapter().apply {
            onItemClickListener = ::onItemClicked
        }
        updateRecyclerViewItems()

        val manager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        viewBinding.listAssets.apply {
            adapter = assetsAdapter
            layoutManager = manager
        }
    }

    private fun onItemClicked(itemPosition: Int) {
        viewModel.assetsList.observe(viewLifecycleOwner) { assets ->
            val action = AssetsFragmentDirections.actionAssetsFragmentToLogInDialog(
                assets!![itemPosition].id ?: 1L
            )
            findNavController().navigate(action)
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        assetsAdapter = null
    }
}
