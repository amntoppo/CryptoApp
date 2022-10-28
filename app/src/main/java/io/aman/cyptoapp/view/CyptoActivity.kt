package io.aman.cyptoapp.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import io.aman.cyptoapp.databinding.ActivityCryptoBinding

@AndroidEntryPoint
class CryptoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCryptoBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCryptoBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}