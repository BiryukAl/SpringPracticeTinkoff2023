package ru.tinkoff.cryptowallet.presentation.screen.assets

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import ru.tinkoff.cryptowallet.R
import ru.tinkoff.cryptowallet.databinding.FragmentAssetsBinding
import ru.tinkoff.cryptowallet.presentation.adapters.AssetsAdapter
import ru.tinkoff.cryptowallet.presentation.base.BaseFragment
import ru.tinkoff.cryptowallet.presentation.screen.assets.add.AddAssetDialog

class AssetsFragment : BaseFragment(R.layout.fragment_assets) {

    private val viewBinding: FragmentAssetsBinding by viewBinding(FragmentAssetsBinding::bind)
    private val viewModel by viewModels<AssetsViewModel>()
    private var assetsAdapter: AssetsAdapter? = null


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initShowAddDialog()
        initRecyclerView()
    }

    private fun initShowAddDialog() {
        val dialog = AddAssetDialog.getInstance()

        viewBinding.btnAddAssets.setOnClickListener {
            dialog.show(childFragmentManager, AddAssetDialog.ADD_ASSET_DIALOG_TAG)
        }
    }

    private fun initRecyclerView() {
        viewModel.getAllAssets()
        assetsAdapter = AssetsAdapter().apply {
            viewModel.assetsList.observe(viewLifecycleOwner) { assets ->
                if (assets != null) {
                    listAssets = assets
                }
            }
            onItemClickListener = ::onItemClicked
        }

        val manager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        viewBinding.listAssets.apply {
            adapter = assetsAdapter
            layoutManager = manager
        }
    }

    private fun onItemClicked(itemPosition: Int) {

    }

    override fun onDestroy() {
        super.onDestroy()
        assetsAdapter = null
    }
}
