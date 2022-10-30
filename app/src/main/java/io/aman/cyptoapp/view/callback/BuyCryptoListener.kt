package io.aman.cyptoapp.view.callback

import io.aman.cyptoapp.data.model.CryptoHolding

interface BuyCryptoListener {
    fun onBuyCryptoClick(item: CryptoHolding)
}