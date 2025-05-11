package com.example.bitcoin_price.bitcoin.presentation.widgets

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.lifecycleScope
import com.example.bitcoin_price.MainActivity
import com.example.bitcoin_price.R
import com.example.bitcoin_price.ui.theme.Bitcoin_priceTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@SuppressLint("CustomSplashScreen")
class SplashScreen : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Bitcoin_priceTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    SplashScreenContent()
                }
            }
        }

        // Inicia a navegação após 3 segundos
        lifecycleScope.launch {
            delay(3000)  // 3 segundos de atraso
            val intent = Intent(this@SplashScreen, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}

@Composable
fun SplashScreenContent() {
    val currentNightMode = androidx.compose.ui.platform.LocalContext.current.resources.configuration.uiMode and
            android.content.res.Configuration.UI_MODE_NIGHT_MASK

    // Corrigindo a seleção da imagem com base no modo noturno
    val logoRes = when (currentNightMode) {
        android.content.res.Configuration.UI_MODE_NIGHT_YES -> R.drawable.logo_dark  // Imagem para modo noturno
        else -> R.drawable.logo_light // Imagem para modo claro
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        // Exibe o logo de acordo com o tema
        Image(
            painter = painterResource(id = logoRes),
            contentDescription = "Bitcoin Logo"
        )

        // CircularProgressIndicator se você quiser exibir algo enquanto carrega
        CircularProgressIndicator(
            modifier = Modifier
                .padding(top = 16.dp)
                .size(50.dp)
        )
    }
}
