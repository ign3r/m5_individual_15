package com.example.m5individual15.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.m5individual15.ui.theme.PrimaryText
import com.example.m5individual15.ui.theme.Purple80

@Composable
fun PatientCard(
    nombre: String,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp)
            .clickable { onClick() }
            .shadow(5.dp),
        colors = CardDefaults.cardColors(
            containerColor = Purple80,
            contentColor = Purple80
        ),
        shape = RoundedCornerShape(10.dp),
        elevation = CardDefaults.cardElevation(11.dp)

    ) {
        Row(horizontalArrangement = Arrangement.SpaceAround) {

            Column(
                modifier = Modifier
                    .padding(15.dp),
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                Text(text = nombre, fontWeight = FontWeight.Bold, color = PrimaryText)
                Spacer(Modifier.height(20.dp))
                Boton(text = "Calcular IMC") {
                }


            }
        }
    }
}