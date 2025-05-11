package com.example.bitcoin_price.bitcoin.presentation.widgets

data class BitCoinUiState(
    val price: Int = 0,
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String? = "Something went wrong",
)