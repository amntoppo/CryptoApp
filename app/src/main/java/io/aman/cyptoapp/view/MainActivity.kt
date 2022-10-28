package io.aman.cyptoapp.view

import android.R.id
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import io.aman.cyptoapp.databinding.ActivityMainBinding


/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var fullscreenContent: TextView
    private lateinit var fullscreenContentControls: LinearLayout
    private val hideHandler = Handler()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            emptyButton.setOnClickListener {
                val intent = Intent(this@MainActivity, CryptoActivity::class.java)
                startActivity(intent)
            }
        }
    }
}