package io.aman.cyptoapp.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import io.aman.cyptoapp.R
import io.aman.cyptoapp.data.model.AllTransaction
import io.aman.cyptoapp.data.model.CryptoHolding
import io.aman.cyptoapp.databinding.LayoutRecentTransactionsItemBinding
import io.aman.cyptoapp.utils.loadUrl
import kotlin.text.Typography.dollar

class TransactionAdapter : ListAdapter<AllTransaction, TransactionAdapter.TransactionViewHolder>(TransactionComparator()) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TransactionViewHolder {
        val binding = LayoutRecentTransactionsItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TransactionViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TransactionViewHolder, position: Int) {
        val currentitem = getItem(position)
        if(currentitem != null) {
            holder.bind(currentitem)
        }
    }

    class TransactionViewHolder(private val binding: LayoutRecentTransactionsItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(currentItem: AllTransaction) {
            binding.apply {
                transactionTitle.text = currentItem.title
                transactionTime.text = currentItem.txn_time
                amountText.text = root.context.getString(R.string.dollar_sign, currentItem.txn_amount)
                subAmountText.text = currentItem.txn_sub_amount
                transactionImage.loadUrl(currentItem.txn_logo)
            }
        }
    }

    class TransactionComparator: DiffUtil.ItemCallback<AllTransaction>() {
        override fun areItemsTheSame(oldItem: AllTransaction, newItem: AllTransaction) =
            oldItem.title == newItem.title


        override fun areContentsTheSame(oldItem: AllTransaction, newItem: AllTransaction) =
            oldItem == newItem
    }
}