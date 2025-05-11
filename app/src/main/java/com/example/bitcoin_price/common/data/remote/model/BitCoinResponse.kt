package com.example.bitcoin_price.common.data.remote.model

data class BitCoinResponse(
    val status: String,
    val name: String,
    val unit: String,
    val period: String,
    val description: String,
    val values: List<PriceValue>
)

data class PriceValue(
    val x: Long,
    val y: Double
)
