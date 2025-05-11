package com.example.bitcoin_price

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.bitcoin_price.bitcoin.presentation.BitcoinPriceViewModel
import com.example.bitcoin_price.ui.theme.Bitcoin_priceTheme

class MainActivity : ComponentActivity() {
    private val bitcoinPriceViewModel by viewModels<BitcoinPriceViewModel> {
        BitcoinPriceViewModel.Factory(this)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Bitcoin_priceTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    BitcoinPriceApp(bitcoinPriceViewModel)
                }
            }
        }
    }
}