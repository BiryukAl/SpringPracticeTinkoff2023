package ru.tinkoff.cryptowallet.presentation.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import dagger.hilt.android.scopes.ActivityRetainedScoped
import ru.tinkoff.cryptowallet.data.cache.entities.CryptoData
import ru.tinkoff.cryptowallet.databinding.CryptoItemBinding
import javax.inject.Inject

@ActivityRetainedScoped
class CryptoAdapter @Inject constructor(
    private val glide: RequestManager,
) : RecyclerView.Adapter<CryptoAdapter.CryptoViewHolder>() {

    var listItems: List<CryptoData> = listOf()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    var onItemClickListener: ((Int) -> Unit)? = null


    inner class CryptoViewHolder(
        private val glide: RequestManager,
        private val viewBinding: CryptoItemBinding,
    ) : RecyclerView.ViewHolder(viewBinding.root) {

        fun bindItem(cryptoData: CryptoData) {
            with(viewBinding) {
                nameCrypto.text = cryptoData.name
                coastCrypto.text = cryptoData.coast.toString()
                glide.load(cryptoData.iconLink).into(iconCurrency)
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
            glide = glide,
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
