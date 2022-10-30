package io.aman.cyptoapp.view

import android.R.id
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import io.aman.cyptoapp.databinding.ActivityMainBinding
import io.aman.cyptoapp.utils.EMPTY
import io.aman.cyptoapp.utils.VALUE


/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            emptyButton.setOnClickListener {
                val intent = Intent(this@MainActivity, CryptoActivity::class.java)
                intent.putExtra("type", EMPTY)
                startActivity(intent)
            }
            valuesButton.setOnClickListener {
                val intent = Intent(this@MainActivity, CryptoActivity::class.java)
                intent.putExtra("type", VALUE)
                startActivity(intent)
            }
        }
    }
}