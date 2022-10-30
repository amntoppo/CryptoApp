package io.aman.cyptoapp.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.aman.cyptoapp.data.api.CryptoApi
import io.aman.cyptoapp.data.model.CryptoHolding
import io.aman.cyptoapp.data.model.CryptoModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CryptoViewModel @Inject constructor(
    private val api: CryptoApi
): ViewModel() {
    private val HoldingsLiveData = MutableLiveData<CryptoModel>()
    //val Holdingsdata: LiveData<CryptoHolding> = HoldingsLiveData

    fun getHoldingList(): LiveData<CryptoModel> {
        viewModelScope.launch {
            val Holding = api.getCryptoData()
            Log.e("holdingviewmodel", Holding.toString())
            HoldingsLiveData.value = Holding
        }
        return HoldingsLiveData
    }

}