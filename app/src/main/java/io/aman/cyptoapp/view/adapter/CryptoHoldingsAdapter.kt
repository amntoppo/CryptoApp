package io.aman.cyptoapp.view.adapter

import android.text.Layout
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import io.aman.cyptoapp.data.model.CryptoHolding
import io.aman.cyptoapp.databinding.LayoutCryptoHoldingsItemBinding
import io.aman.cyptoapp.utils.loadUrl

class CryptoHoldingsAdapter: ListAdapter<CryptoHolding, CryptoHoldingsAdapter.CryptoHoldingViewHolder>(CryptoHoldingComparator()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CryptoHoldingViewHolder {
        val binding = LayoutCryptoHoldingsItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CryptoHoldingViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CryptoHoldingViewHolder, position: Int) {
        val currentitem = getItem(position)
        if(currentitem != null) {
            holder.bind(currentitem)
        }
    }

    class CryptoHoldingViewHolder(private val binding: LayoutCryptoHoldingsItemBinding) : RecyclerView.ViewHolder(binding.root)  {
        fun bind(currentitem: CryptoHolding) {
            binding.apply {
                coinTitle.text = currentitem.title

                coinImage.loadUrl(currentitem.logo)
            }
        }

    }

    class CryptoHoldingComparator: DiffUtil.ItemCallback<CryptoHolding>() {
        override fun areItemsTheSame(oldItem: CryptoHolding, newItem: CryptoHolding) =
            oldItem.title == newItem.title


        override fun areContentsTheSame(oldItem: CryptoHolding, newItem: CryptoHolding) =
            oldItem == newItem

    }
}