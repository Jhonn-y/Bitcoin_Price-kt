package com.example.bitcoin_price.bitcoin.presentation.widgets

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.example.bitcoin_price.R

// Função Composable para o MarkerView
@Composable
fun CustomGraphMarker(value: Float) {
    // Define a cor com base no valor
    val textColor = if (value > 0) {
        colorResource(id = R.color.green) // Cor verde para aumento
    } else {
        colorResource(id = R.color.red) // Cor vermelha para queda
    }

    Box(
        modifier = Modifier
            .padding(8.dp)
    ) {
        // Exibe o valor do Bitcoin formatado com "$"
        Text(
            text = "$${"%.2f".format(value)}",
            style = MaterialTheme.typography.bodyMedium,
            color = textColor
        )
    }
}
