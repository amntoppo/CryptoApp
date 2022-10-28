package io.aman.cyptoapp.viewmodel

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.aman.cyptoapp.data.api.CryptoApi
import javax.inject.Inject

@HiltViewModel
class CryptoViewModel @Inject constructor(
    private val api: CryptoApi
): ViewModel() {

}