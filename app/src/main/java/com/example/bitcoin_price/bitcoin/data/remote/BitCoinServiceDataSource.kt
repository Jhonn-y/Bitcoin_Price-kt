package com.example.bitcoin_price.bitcoin.data.remote

import android.accounts.NetworkErrorException
import com.example.bitcoin_price.common.data.remote.model.BitCoinResponse

class BitCoinServiceDataSource(
    private val bitcoinService: BitcoinService,
) {
    suspend fun getPrice(): Result<BitCoinResponse> {
        return try {
            val response = bitcoinService.getPrice()
            if (response.isSuccessful) {
                val bitCoinResponse = response.body()
                if (bitCoinResponse != null) {
                    Result.success(bitCoinResponse)
                } else {
                    Result.failure(Exception("Resposta da API vazia"))
                }
            } else {
                Result.failure(NetworkErrorException(response.message()))
            }
        } catch (ex: Exception) {
            Result.failure(ex)
        }
    }
}
