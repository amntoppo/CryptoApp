package io.aman.cyptoapp.view

import android.os.Build
import android.os.Bundle
import android.text.format.DateUtils
import android.view.View
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import io.aman.cyptoapp.R
import io.aman.cyptoapp.data.model.AllTransaction
import io.aman.cyptoapp.data.model.CryptoHolding
import io.aman.cyptoapp.databinding.ActivityCryptoBinding
import io.aman.cyptoapp.utils.EMPTY
import io.aman.cyptoapp.utils.getTimeAgo
import io.aman.cyptoapp.view.adapter.CryptoHoldingsAdapter
import io.aman.cyptoapp.view.adapter.CurrentPricesAdapter
import io.aman.cyptoapp.view.adapter.TransactionAdapter
import io.aman.cyptoapp.view.callback.BuyCryptoListener
import io.aman.cyptoapp.viewmodel.CryptoViewModel
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.util.*


@AndroidEntryPoint
class CryptoActivity : AppCompatActivity(), BuyCryptoListener {
    private lateinit var binding: ActivityCryptoBinding
    private val viewModel: CryptoViewModel by viewModels()
    private var pageType: String? = EMPTY
    private lateinit var cryptoHoldingsAdapter: CryptoHoldingsAdapter
    private lateinit var currentPricesAdapter: CurrentPricesAdapter
    private lateinit var transactionAdapter: TransactionAdapter

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityCryptoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        pageType = intent.getStringExtra("type")
        cryptoHoldingsAdapter = CryptoHoldingsAdapter(pageType, this)
        transactionAdapter = TransactionAdapter()
        currentPricesAdapter = CurrentPricesAdapter()
        setupRecyclerViews()

        binding.apply {
            if(pageType.equals(EMPTY)) {
                layoutAccountDetails.depositButton.visibility = View.VISIBLE
                layoutAccountDetails.amountText.visibility = View.GONE
                layoutAccountDetails.subAmountText.visibility = View.GONE
                observeEmptyData()
            } else {
                layoutAccountDetails.depositButton.visibility = View.GONE
                layoutAccountDetails.amountText.visibility = View.VISIBLE
                layoutAccountDetails.subAmountText.visibility = View.VISIBLE
                observeValueData()
            }


        }


    }
    fun observeEmptyData() {
        binding.apply {
            viewModel.getEmptyCryptoData().observe(this@CryptoActivity) {
                layoutAccountDetails.cryptoAccountText.text = it.crypto_balance.title
                layoutAccountDetails.amountText.text = it.crypto_balance.current_bal_in_usd
                layoutAccountDetails.subtitleTextview.text = it.crypto_balance.subtitle
                cryptoHoldingsAdapter.submitList(it.your_crypto_holdings)
                transactionAdapter.submitList(it.all_transactions)
                currentPricesAdapter.submitList(it.crypto_prices)
            }
        }

//        viewModel.getTransaction().observe(this@CryptoActivity) {
//            transactionAdapter.submitList(it)
//        }
        viewModel.TransactionsLiveData.observe(this) {
            transactionAdapter.submitList(it)
            transactionAdapter.notifyDataSetChanged()
        }
    }
    fun observeValueData() {
        binding.apply {
            viewModel.getValueCryptoData().observe(this@CryptoActivity) {
                layoutAccountDetails.cryptoAccountText.text = it.crypto_balance.title
                layoutAccountDetails.amountText.text = getString(R.string.dollar_sign, it.crypto_balance.current_bal_in_usd)
                layoutAccountDetails.subtitleTextview.text = it.crypto_balance.subtitle
                cryptoHoldingsAdapter.submitList(it.your_crypto_holdings)
                transactionAdapter.submitList(it.all_transactions)
                currentPricesAdapter.submitList(it.crypto_prices)
            }
        }
    }
    fun setupRecyclerViews() {
        binding.apply {
            cryptoHoldingRecyclerView.apply {
                adapter = cryptoHoldingsAdapter
                layoutManager = LinearLayoutManager(this@CryptoActivity)
            }
            recentTransactionRecyclerView.apply {
                adapter = transactionAdapter
                layoutManager = LinearLayoutManager(this@CryptoActivity)
            }
            currentPricesRecyclerView.apply {
                adapter = currentPricesAdapter
                layoutManager =
                    LinearLayoutManager(this@CryptoActivity, LinearLayoutManager.HORIZONTAL, false)
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBuyCryptoClick(item: CryptoHolding) {
       // val sdf = SimpleDateFormat("dd/M/yyyy hh:mm:ss")
       // val currentDate = sdf.format(Date())
        val niceDateStr: String = DateUtils.getRelativeTimeSpanString(
            Date().time,
            Calendar.getInstance().getTimeInMillis(),
            DateUtils.MINUTE_IN_MILLIS
        ) as String

        val newTransaction: AllTransaction = AllTransaction(item.title + " Bought", item.current_bal_in_usd, item.logo,item.current_bal_in_token , Date().getTimeAgo())
        viewModel.updateTransaction(newTransaction)

       // transactionAdapter.submitList()
    }
}