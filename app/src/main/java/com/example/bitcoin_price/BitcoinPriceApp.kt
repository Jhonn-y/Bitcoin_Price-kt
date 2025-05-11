package com.example.bitcoin_price

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.bitcoin_price.bitcoin.presentation.widgets.BitcoinHomeScreen
import com.example.bitcoin_price.bitcoin.presentation.BitcoinPriceViewModel

@Composable
fun BitcoinPriceApp(
    bitcoinPriceViewModel: BitcoinPriceViewModel,

    ) {
    val navigation = rememberNavController()
    NavHost(
        navController = navigation,
        startDestination = "bitcoinHome"
    ) {
        composable(
            route = "bitcoinHome"
        ) {
            BitcoinHomeScreen(navController = navigation,
                bitcoinPriceViewModel = bitcoinPriceViewModel)
        }
    }

}