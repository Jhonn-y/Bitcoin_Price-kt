package com.example.bitcoin_price.bitcoin.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bitcoin_price.bitcoin.data.BitcoinPriceRepo
import com.example.bitcoin_price.common.data.remote.model.PriceValue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class BitcoinPriceViewModel(
    private val bitcoinRepo: BitcoinPriceRepo
) : ViewModel() {

    private val _marketPrice = MutableLiveData<List<PriceValue>>()
    val marketPrice: LiveData<List<PriceValue>> = _marketPrice

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> = _errorMessage

    // Função para buscar os preços
    fun fetchMarketPrice() {
        viewModelScope.launch(Dispatchers.IO) {
            val result = bitcoinRepo.getPrice()
            withContext(Dispatchers.Main) {
                result.onSuccess { bitCoinResponse ->
                    _marketPrice.value = bitCoinResponse.values
                }.onFailure { exception ->
                    _errorMessage.value = exception.localizedMessage
                }
            }
        }
    }

    // Função para filtrar os dados de acordo com os dias
    fun filterData(days: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val result = bitcoinRepo.getPrice() // Aqui você pode buscar os preços novamente
            withContext(Dispatchers.Main) {
                result.onSuccess { bitCoinResponse ->
                    val filteredData = filterByDays(bitCoinResponse.values, days)
                    _marketPrice.value = filteredData
                }.onFailure { exception ->
                    _errorMessage.value = exception.localizedMessage
                }
            }
        }
    }

    // Função para filtrar os dados de acordo com os dias
    private fun filterByDays(values: List<PriceValue>, days: Int): List<PriceValue> {
        // Aqui você pode implementar a lógica para filtrar os dados conforme os dias
        val currentTime = System.currentTimeMillis()
        val filteredData = when (days) {
            1 -> values.filter { it.x > currentTime - 1 * 24 * 60 * 60 * 1000L } // Último 1 dia
            7 -> values.filter { it.x > currentTime - 7 * 24 * 60 * 60 * 1000L } // Últimos 7 dias
            30 -> values.filter { it.x > currentTime - 30 * 24 * 60 * 60 * 1000L } // Últimos 30 dias
            else -> values
        }
        return filteredData
    }

    fun calculateStats(values: List<PriceValue>): Map<String, Any> {
        if (values.isEmpty()) {
            return mapOf(
                "Open" to "-",
                "High" to "-",
                "Average" to "-",
                "Close" to "-",
                "Low" to "-",
                "Change" to "-",
                "ChangePercentage" to "-"
            )
        }

        val sortedValues = values.sortedBy { it.x }
        val open = sortedValues.first().y
        val close = sortedValues.last().y
        val high = sortedValues.maxOf { it.y }
        val low = sortedValues.minOf { it.y }
        val average = sortedValues.map { it.y }.average()
        val change = close - open
        val changePercentage = ((change / open) * 100)

        return mapOf(
            "Open" to open,
            "High" to high,
            "Average" to average,
            "Close" to close,
            "Low" to low,
            "Change" to change,
            "ChangePercentage" to "%.2f%%".format(changePercentage)
        )
    }
}
