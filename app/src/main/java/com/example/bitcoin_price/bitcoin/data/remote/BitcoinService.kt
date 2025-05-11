package com.example.bitcoin_price.bitcoin.data.remote

import com.example.bitcoin_price.common.data.remote.model.BitCoinResponse
import retrofit2.Response
import retrofit2.http.GET

interface BitcoinService {
    @GET("charts/market-price?format=json")
    suspend fun getPrice(): Response<BitCoinResponse>

}