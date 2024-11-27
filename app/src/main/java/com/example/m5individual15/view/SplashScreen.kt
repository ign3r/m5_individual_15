package com.example.m5individual15.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.m5individual15.R

@Composable
fun SplashScreen(onTimeout: () -> Unit) {
    // Simular un retraso para la pantalla de carga
    LaunchedEffect(Unit) {
        kotlinx.coroutines.delay(2000) // Espera 2 segundos
        onTimeout()
    }

    // Diseño del Splash Screen
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            // Logo o imagen principal
            Image(
                painter = painterResource(id = R.drawable.splash),
                contentDescription = "Logo de la App",
                modifier = Modifier
                    .size(300.dp)
                    .clip(CircleShape)
            )
            Spacer(modifier = Modifier.height(16.dp))
            // Texto opcional
            Text(
                text = "Bienvenido a la App",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )
        }
    }
}
