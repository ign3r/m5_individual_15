package com.example.m5individual15.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.m5individual15.model.ImcState
import java.text.DecimalFormat

class IMCViewModel : ViewModel() {
    var state by mutableStateOf(ImcState())
        private set

    // Lista de pacientes
    var pacientes by mutableStateOf(listOf<Paciente>())
        private set

    // Agregar un nuevo paciente
    fun agregarPaciente( nombre: String, edad: String, imc: String, salud: String, genero: String) {
        val id =pacientes.size
        val nuevoPaciente = Paciente(id,nombre, edad, imc, salud, genero)
        pacientes = pacientes + nuevoPaciente
    }

    fun editarPaciente(id: Int, nombre: String, edad: String, imc: String, salud: String, genero: String) {
        // Verificar que el índice esté dentro de los límites de la lista
        if (id in pacientes.indices) {
            // Crear una copia mutable de la lista
            val listaActualizada = pacientes.toMutableList()
            // Modificar el elemento en la posición indicada
            listaActualizada[id] = Paciente(id,nombre, edad, imc, salud, genero)
            // Actualizar la lista inmutable
            pacientes = listaActualizada
        } else {
            // Manejo del caso en el que el índice es inválido
            throw IndexOutOfBoundsException("El índice $id está fuera de los límites de la lista.")
        }
    }

    fun obtenerPacientePorId(id: Int): Paciente? {
        return pacientes.find { it.id == id }
    }

    // Cálculo del IMC (sin cambios)
    fun calcularIMC(edad: String, peso: String, altura: String, sexo: String): Pair<String, Boolean> {
        var imc: Double = 0.0
        var showAlert: Boolean = false
        try {
            peso.toDouble()
            altura.toDouble()
            edad.toDouble()
        } catch (e: NumberFormatException) {
            showAlert = true
        }

        if (sexo.isNotEmpty() && !showAlert && altura.toDouble() > 0 && peso.toDouble() > 0 && edad.toDouble() > 0) {
            imc = (peso.toDouble() / (alturaAcm(altura.toDouble()) * alturaAcm(altura.toDouble())))
            val dec = DecimalFormat("#.##")
            return Pair(dec.format(imc), false)
        } else {
            showAlert = true
            return Pair("0.0", true)
        }
    }

    private fun alturaAcm(altura: Double): Double = altura / 100
}

data class Paciente(
    val id: Int,
    val nombre: String,
    val edad: String,
    val imc: String,
    val salud: String,
    val genero: String
)

