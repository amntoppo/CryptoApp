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

}