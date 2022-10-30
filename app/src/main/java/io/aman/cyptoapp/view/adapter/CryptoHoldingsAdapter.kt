package io.aman.cyptoapp.view.adapter

import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import io.aman.cyptoapp.R
import io.aman.cyptoapp.data.model.CryptoHolding
import io.aman.cyptoapp.databinding.LayoutCryptoHoldingsItemBinding
import io.aman.cyptoapp.utils.EMPTY
import io.aman.cyptoapp.utils.loadUrl

class CryptoHoldingsAdapter(private val dataType: String?): ListAdapter<CryptoHolding, CryptoHoldingsAdapter.CryptoHoldingViewHolder>(CryptoHoldingComparator()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CryptoHoldingViewHolder {
        val binding = LayoutCryptoHoldingsItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CryptoHoldingViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CryptoHoldingViewHolder, position: Int) {
        val currentitem = getItem(position)
        if(currentitem != null) {
            holder.bind(currentitem, dataType)
        }
    }

    class CryptoHoldingViewHolder(private val binding: LayoutCryptoHoldingsItemBinding) : RecyclerView.ViewHolder(binding.root)  {
        fun bind(currentitem: CryptoHolding, dataType: String?) {
            binding.apply {
                if(dataType.equals(EMPTY)) {
                    amountText.visibility = View.GONE
                    subAmountText.visibility = View.GONE
                    subtitleTextview.visibility = View.GONE
                    buyCoinButton.visibility = View.VISIBLE
                    depositCoinButton.visibility = View.VISIBLE
                } else {
                    amountText.visibility = View.VISIBLE
                    subAmountText.visibility = View.VISIBLE
                    subtitleTextview.visibility = View.VISIBLE
                    buyCoinButton.visibility = View.GONE
                    depositCoinButton.visibility = View.GONE
                }
                coinTitle.text = currentitem.title
                amountText.text = root.context.getString(R.string.dollar_sign, currentitem.current_bal_in_usd)
                subtitleTextview.text = currentitem.current_bal_in_token + " " + currentitem.title
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