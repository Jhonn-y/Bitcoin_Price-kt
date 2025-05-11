package com.example.bitcoin_price.common.data.remote

import com.example.bitcoin_price.bitcoin.data.remote.BitcoinService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val BASE_URL = "https://api.blockchain.info"

    val retrofitInstance: BitcoinService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(BitcoinService::class.java)
    }
}