package io.aman.cyptoapp.data.model

data class AllTransaction(
    var title: String,
    val txn_amount: String,
    val txn_logo: String,
    val txn_sub_amount: String,
    val txn_time: String
)