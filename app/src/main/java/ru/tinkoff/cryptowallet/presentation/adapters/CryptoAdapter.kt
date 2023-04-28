package ru.tinkoff.cryptowallet.presentation.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.tinkoff.cryptowallet.data.cache.entities.CryptoData
import ru.tinkoff.cryptowallet.databinding.CryptoItemBinding

class CryptoAdapter() : RecyclerView.Adapter<CryptoAdapter.CryptoViewHolder>() {

    var listItems: List<CryptoData> = listOf()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    var onItemClickListener: ((Int) -> Unit)? = null

    inner class CryptoViewHolder(
        private val viewBinding: CryptoItemBinding,
    ) : RecyclerView.ViewHolder(viewBinding.root) {

        fun bindItem(cryptoData: CryptoData) {
            // TODO: Add Download image
            with(viewBinding) {
                nameCrypto.text = cryptoData.name
                coastCrypto.text = cryptoData.coast.toString()
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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CryptoViewHolder =
        CryptoViewHolder(
            viewBinding = CryptoItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        )

    override fun getItemCount(): Int = listItems.size

    override fun onBindViewHolder(holder: CryptoViewHolder, position: Int) {
        holder.bindItem(listItems[position])
    }
}
