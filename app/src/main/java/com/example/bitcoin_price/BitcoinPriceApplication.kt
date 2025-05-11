package com.example.bitcoin_price

import android.app.Application
import com.example.bitcoin_price.bitcoin.data.BitcoinPriceRepo
import com.example.bitcoin_price.bitcoin.data.remote.BitCoinServiceDataSource
import com.example.bitcoin_price.bitcoin.data.remote.BitcoinService
import com.example.bitcoin_price.common.data.remote.RetrofitClient

class BitcoinPriceApplication : Application(){

    private val bitcoinService by lazy {
        RetrofitClient.retrofitInstance.create(BitcoinService::class.java)
    }

    private val remoteDataSource: BitCoinServiceDataSource by lazy {
        BitCoinServiceDataSource(bitcoinService)
    }

    val repository: BitcoinPriceRepo by lazy {
        BitcoinPriceRepo(
            remote = remoteDataSource
        )
    }
}