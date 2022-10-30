package io.aman.cyptoapp.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import io.aman.cyptoapp.R
import io.aman.cyptoapp.data.model.AllTransaction
import io.aman.cyptoapp.data.model.CryptoPrice
import io.aman.cyptoapp.databinding.LayoutCurrentPriceItemBinding
import io.aman.cyptoapp.utils.loadUrl

class CurrentPricesAdapter: ListAdapter<CryptoPrice, CurrentPricesAdapter.PricesViewHolder>(PricesComparator()) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CurrentPricesAdapter.PricesViewHolder {
        val binding = LayoutCurrentPriceItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PricesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CurrentPricesAdapter.PricesViewHolder, position: Int) {
        val currentItem = getItem(position)
        if(currentItem != null) {
            holder.bind(currentItem)
        }
    }

    class PricesViewHolder(private val binding: LayoutCurrentPriceItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(currentItem: CryptoPrice) {
            binding.apply {
                coinName.text = currentItem.title
                coinAmount.text = root.context.getString(R.string.dollar_sign, currentItem.current_price_in_usd)
                iconImage.loadUrl(currentItem.logo)
            }
        }
    }
    class PricesComparator: DiffUtil.ItemCallback<CryptoPrice>() {
        override fun areItemsTheSame(oldItem: CryptoPrice, newItem: CryptoPrice) =
            oldItem.title == newItem.title


        override fun areContentsTheSame(oldItem: CryptoPrice, newItem: CryptoPrice) =
            oldItem == newItem
    }
}