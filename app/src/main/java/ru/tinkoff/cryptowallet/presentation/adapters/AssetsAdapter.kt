package ru.tinkoff.cryptowallet.presentation.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import ru.tinkoff.cryptowallet.data.cache.entities.Assets
import ru.tinkoff.cryptowallet.databinding.AssetsItemBinding

class AssetsAdapter() : RecyclerView.Adapter<AssetsAdapter.AssetsViewHolder>() {

    var listAssets: List<Assets> = listOf()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }
    var onItemClickListener: ((Int) -> Unit)? = null

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): AssetsViewHolder =
        AssetsViewHolder(
            viewBinding =
            AssetsItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        )

    override fun getItemCount(): Int =
        listAssets.size


    override fun onBindViewHolder(holder: AssetsViewHolder, position: Int) {
        holder.bindItem(listAssets[position])
    }

    inner class AssetsViewHolder(
        private val viewBinding: AssetsItemBinding,
    ) : RecyclerView.ViewHolder(viewBinding.root) {
        fun bindItem(assets: Assets) {
            // TODO: Add Download image
            with(viewBinding) {
                nameAssets.text = assets.name
                lockAssets.isVisible = !assets.password.isNullOrEmpty()
            }
        }

        init {
            with(viewBinding) {
                root.setOnClickListener {
                    onItemClickListener?.invoke(bindingAdapterPosition)
                }
            }
        }
    }
}
