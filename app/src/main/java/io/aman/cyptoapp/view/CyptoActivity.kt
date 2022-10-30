package io.aman.cyptoapp.view

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import io.aman.cyptoapp.databinding.ActivityCryptoBinding
import io.aman.cyptoapp.view.adapter.CryptoHoldingsAdapter
import io.aman.cyptoapp.view.adapter.TransactionAdapter
import io.aman.cyptoapp.viewmodel.CryptoViewModel

@AndroidEntryPoint
class CryptoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCryptoBinding
    private val viewModel: CryptoViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        val cryptoHoldingsAdapter = CryptoHoldingsAdapter()
        val transactionAdapter = TransactionAdapter()
        super.onCreate(savedInstanceState)
        binding = ActivityCryptoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            cryptoHoldingRecyclerView.apply {
                adapter = cryptoHoldingsAdapter
                layoutManager = LinearLayoutManager(this@CryptoActivity)
            }
            recentTransactionRecyclerView.apply {
                adapter = transactionAdapter
                layoutManager = LinearLayoutManager(this@CryptoActivity)
            }
            viewModel.getHoldingList().observe(this@CryptoActivity) {
                cryptoHoldingsAdapter.submitList(it.your_crypto_holdings)
                transactionAdapter.submitList(it.all_transactions)
            }
        }
    }
}