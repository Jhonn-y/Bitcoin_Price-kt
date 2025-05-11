package com.example.bitcoin_price.bitcoin.data

import com.example.bitcoin_price.bitcoin.data.remote.BitCoinServiceDataSource
import com.example.bitcoin_price.common.data.remote.model.BitCoinResponse

class BitcoinPriceRepo(
    private val remote: BitCoinServiceDataSource,
) {
    suspend fun getPrice(): Result<BitCoinResponse> {
        return try {
            remote.getPrice()
        } catch (ex: Exception) {
            ex.printStackTrace()
            Result.failure(ex)
        }
    }
}
