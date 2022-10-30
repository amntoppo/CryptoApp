package io.aman.cyptoapp.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.aman.cyptoapp.data.api.CryptoApi
import io.aman.cyptoapp.data.model.AllTransaction
import io.aman.cyptoapp.data.model.CryptoHolding
import io.aman.cyptoapp.data.model.CryptoModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CryptoViewModel @Inject constructor(
    private val api: CryptoApi
): ViewModel() {
    private val HoldingsLiveData = MutableLiveData<CryptoModel>()
    public var TransactionsLiveData: MutableLiveData<MutableList<AllTransaction>> = MutableLiveData()

    fun getValueCryptoData(): LiveData<CryptoModel> {
        viewModelScope.launch {
            val Holding = api.getCryptoData()
            HoldingsLiveData.value = Holding
        }
        return HoldingsLiveData
    }
    fun getEmptyCryptoData(): LiveData<CryptoModel> {
        viewModelScope.launch {
            val Holding = api.getCryptoEmptyData()
            HoldingsLiveData.value = Holding
        }
        return HoldingsLiveData
    }

    fun updateTransaction(transaction: AllTransaction) {
        val list: ArrayList<AllTransaction>
        if(TransactionsLiveData.value != null) {
            list = TransactionsLiveData.value as ArrayList<AllTransaction>

        }
        else {
           list =  ArrayList<AllTransaction>()
        }
        list.add(transaction)
       // TransactionsLiveData.value?.add(transaction)
        TransactionsLiveData.value = list
        Log.e("transaction", TransactionsLiveData.value.toString())
    }

//    fun getTransaction(): LiveData<List<AllTransaction>> {
//        return TransactionsLiveData
//    }

}