package com.example.bitcoin_price.bitcoin.presentation.widgets

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.bitcoin_price.bitcoin.presentation.BitcoinPriceViewModel
import com.example.bitcoin_price.common.data.remote.model.PriceValue

@Composable
fun BitcoinHomeScreen(
    navController: NavController,
    bitcoinPriceViewModel: BitcoinPriceViewModel = viewModel()
) {
    // Observa o estado do preço usando LiveData
    val marketPrice by bitcoinPriceViewModel.marketPrice.observeAsState(emptyList())
    val errorMessage by bitcoinPriceViewModel.errorMessage.observeAsState()

    // Estado para a data filtrada
    var date by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Exibe o estado de carregamento, erro ou o preço
        if (marketPrice.isEmpty()) {
            CircularProgressIndicator()
        } else {
            // Se houver preços, exibe
            Text("Preço do Bitcoin: ${marketPrice.last().y} USD", style = MaterialTheme.typography.headlineMedium)
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Campo de data
        OutlinedTextField(
            value = date,
            onValueChange = { date = it },
            label = { Text("Data (YYYY-MM-DD)") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Botão para buscar o preço por data
        Button(
            onClick = {
                bitcoinPriceViewModel.fetchMarketPrice()
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Buscar Preço Histórico")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Exibe o erro, caso haja
        if (errorMessage != null) {
            Text("Erro: $errorMessage", color = Color.Red)
        }

        // Se houver dados para o gráfico, exibe
        if (marketPrice.isNotEmpty()) {
            // Passando os dados filtrados para o gráfico
            Chart(data = marketPrice) // Aqui você pode passar os dados reais
        }

        // Atualização dos filtros para o gráfico
        FilterButtons { days ->
            bitcoinPriceViewModel.filterData(days)
        }
    }
}

// Componente de botões de filtro
@Composable
fun FilterButtons(onFilterSelected: (Int) -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        RadioButtonWithText("1D", onFilterSelected, 1)
        RadioButtonWithText("7D", onFilterSelected, 7)
        RadioButtonWithText("30D", onFilterSelected, 30)
    }
}

@Composable
fun RadioButtonWithText(text: String, onFilterSelected: (Int) -> Unit, days: Int) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        RadioButton(selected = false, onClick = { onFilterSelected(days) })
        Text(text)
    }
}

// Placeholder para o gráfico, substitua com o seu gráfico real
@Composable
fun Chart(data: List<PriceValue>) {
    // Aqui você pode usar um gráfico real, como MPAndroidChart ou Compose Charts.
    Box(modifier = Modifier.fillMaxWidth().height(200.dp)) {
        Text("Gráfico de Preços", modifier = Modifier.align(Alignment.Center))
        // Exemplo de gráfico usando os dados
        // Exemplo: ChartComponent(data = data) - você deve implementar essa parte
    }
}
