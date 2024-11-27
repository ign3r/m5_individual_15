package com.example.m5individual15.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.m5individual15.components.Alert
import com.example.m5individual15.components.Boton
import com.example.m5individual15.components.CustomOutlinedTextField
import com.example.m5individual15.components.CustomOutlinedTextFieldTexto
import com.example.m5individual15.components.EspacioNormal
import com.example.m5individual15.components.MultiBoton
import com.example.m5individual15.components.Texto
import com.example.m5individual15.components.estadoSalud
import com.example.m5individual15.viewmodel.IMCViewModel

@Composable
fun DetailView(viewModel: IMCViewModel, navController: NavHostController) {

    Scaffold(modifier = Modifier
        .fillMaxSize(),
        containerColor = Color.White
    ) {
        ContentDetailView(it,viewModel)
    }

}

@Composable
fun ContentDetailView(paddingValues: PaddingValues, viewModel: IMCViewModel) {
    Column(
        modifier = Modifier
            .padding(paddingValues)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        var nombre by remember { mutableStateOf("") }
        var edad by remember { mutableStateOf("") }
        var altura by remember { mutableStateOf("") }
        var peso by remember { mutableStateOf("") }
        var imc by remember { mutableStateOf("0.0") }
        var showAlert by remember { mutableStateOf(false) }
        var sexo by remember { mutableStateOf("") }

        MultiBoton(onSelectionChanged = { selection -> sexo = selection })
        EspacioNormal()
        CustomOutlinedTextField(edad, { edad = it }, "Edad")
        CustomOutlinedTextField(peso, { peso = it }, "Peso (kg)")
        CustomOutlinedTextField(altura, { altura = it }, "Altura (cm)")
        CustomOutlinedTextFieldTexto(nombre, { nombre = it }, "Nombre")
        EspacioNormal()
        Boton(text = "Calcular") {
            val result = viewModel.calcularIMC(edad, peso, altura, sexo)
            imc = result.first
            showAlert = result.second
        }
        EspacioNormal()
        Texto(imc)
        if(imc.toDouble()>0){
        estadoSalud(imc = imc)
        EspacioNormal()
        Boton(text = "Guardar") {
            if (imc.toDouble() > 0 && nombre.isNotEmpty()) {
                val salud = estadoSaludTexto(imc.toDouble())
                viewModel.agregarPaciente(nombre, edad, imc, salud, sexo)
            }
        }
        }else Spacer(modifier = Modifier.height(50.dp))

        if (showAlert) {
            Alert(
                title = "Alerta",
                msj = "Ingresa los datos adecuadamente",
                confirmText = "Aceptar",
                onConfirmClick = { showAlert = false }
            ) {}
        }
    }
}

fun estadoSaludTexto(imc: Double): String {
    return when {
        imc < 18.5 -> "Bajo peso"
        imc < 24.9 -> "Peso normal"
        imc < 29.9 -> "Sobrepeso"
        imc < 34.9 -> "Obesidad Clase I"
        imc < 39.9 -> "Obesidad Clase II"
        else -> "Obesidad Clase III"
    }
}