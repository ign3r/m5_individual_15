package com.example.m5individual15.view

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.m5individual15.R
import com.example.m5individual15.ui.theme.Purple80
import com.example.m5individual15.viewmodel.IMCViewModel

//
//
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun HomeView(viewModel: IMCViewModel, navController: NavHostController,context: Context) {
//    var cardsList by remember { mutableStateOf(listOf<String>()) }
//    var IMCList by remember { mutableStateOf(listOf<String>()) }
//    var EdadList by remember { mutableStateOf(listOf<String>()) }
//    var GeneroList by remember { mutableStateOf(listOf<String>()) }
//    var saludList by remember { mutableStateOf(listOf<String>()) }
//
//
//    var showAlert by remember { mutableStateOf(false) }
//
//    // Cargar las tarjetas guardadas al inicio
//    LaunchedEffect(Unit) {
//        DataStoreManager.getCards(context).collect { savedCards ->
//            cardsList = savedCards.toList()
//        }
//    }
//
//    Scaffold(
//        modifier = Modifier.fillMaxSize(),
//        containerColor = Color.White,
//        topBar = {
//            TopAppBar(
//                title = { Text(text = stringResource(id = R.string.app_name)) },
//            )
//        },
//        floatingActionButton = {
//            FloatingActionButton(
//                onClick = { showAlert = true },
//                containerColor = Purple80
//            ) {
//                Icon(imageVector = Icons.Default.Add, contentDescription = "Añadir paciente")
//            }
//        }
//    ) { innerPadding ->
//        Column(
//            modifier = Modifier
//                .padding(innerPadding)
//                .fillMaxSize(),
//            horizontalAlignment = Alignment.CenterHorizontally,
//        ) {
//            LazyColumn {
//                items(cardsList.size) { index ->
//                    CardItem(nombre = cardsList[index],edad = cardsList[index],imc = cardsList[index],salud = cardsList[index],genero = cardsList[index], navController)
//                }
//            }
//            if (showAlert) {
//                CrearCard(
//                    onDismiss = { showAlert = false },
//                    onConfirm = { newName ->
//                        if (newName.isNotBlank()) {
//                            val updatedList = cardsList + newName
//                            cardsList = updatedList
//                            // Guardar las tarjetas en DataStore
//                            CoroutineScope(Dispatchers.IO).launch {
//                                DataStoreManager.saveCards(context, updatedList.toSet())
//                            }
//                            showAlert = false
//                        }
//                    }
//                )
//            }
//        }
//    }
//}
//
//@Composable
//fun CardItem(nombre: String, edad: String, imc:String, salud:String, genero:String, navController: NavHostController) {
//    Card(
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(bottom = 5.dp),
//        shape = RoundedCornerShape(12.dp)
//    ) {
//        Row(horizontalArrangement = Arrangement.SpaceAround) {
//            Column(modifier = Modifier.padding(16.dp)) {
//                Text(
//                    text = nombre,
//                    style = TextStyle(fontWeight = FontWeight.Bold)
//                )
//                Spacer(modifier = Modifier.height(8.dp))
//                Text(
//                    text = edad,
//                    style = TextStyle(fontWeight = FontWeight.Bold)
//                )
//                Spacer(modifier = Modifier.height(8.dp))
//                Text(
//                    text = imc,
//                    style = TextStyle(fontWeight = FontWeight.Bold)
//                )
//                Button(onClick = { navController.navigate("DetailView") }) {
//                    Text("Calcular IMC")
//                }
//
//            }
//            Column(modifier = Modifier.padding(16.dp),
//                verticalArrangement = Arrangement.Center,
//                horizontalAlignment = Alignment.CenterHorizontally) {
//                Image(painter = painterResource(id = R.drawable.baseline_male_24),
//                    contentDescription = "Test Imagen",
//                    modifier = Modifier.size(40.dp))
//            }
//        }
//    }
//}
//
//
//@Composable
//fun CrearCard(onDismiss: () -> Unit, onConfirm: (String) -> Unit) {
//    var nombre by remember { mutableStateOf("") }
//
//    AlertDialog(
//        onDismissRequest = onDismiss,
//        title = {
//            Text(text = "Ingresa Paciente")
//        },
//        text = {
//            Column {
//                Text(text = "Escribe el nombre del paciente:")
//                Spacer(modifier = Modifier.height(8.dp))
//                TextField(
//                    value = nombre,
//                    onValueChange = { nombre = it },
//                    label = { Text("Nombre") }
//                )
//            }
//        },
//        confirmButton = {
//            TextButton(
//                onClick = { onConfirm(nombre) }
//            ) {
//                Text("OK")
//            }
//        },
//        dismissButton = {
//            TextButton(
//                onClick = onDismiss
//            ) {
//                Text("Cancelar")
//            }
//        }
//    )
//}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeView(viewModel: IMCViewModel, navController: NavHostController, context: Context) {
    val pacientes = viewModel.pacientes

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = Color.White,
        topBar = {
            TopAppBar(
                title = { Text(text = stringResource(id = R.string.app_name)) },
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate("DetailView") },
                containerColor = Purple80
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Añadir paciente")
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            LazyColumn {
                items(pacientes.size) { index ->
                    val paciente = pacientes[index]
                    CardItem(
                        id=paciente.id,
                        nombre = paciente.nombre,
                        edad = paciente.edad,
                        imc = paciente.imc,
                        salud = paciente.salud,
                        genero = paciente.genero,
                        navController
                    )
                }
            }
        }
    }
}
@Composable
fun CardItem(id:Int, nombre: String, edad: String, imc:String, salud:String, genero:String, navController: NavHostController) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 5.dp),
        shape = RoundedCornerShape(12.dp)
    ) {
        Row(horizontalArrangement = Arrangement.SpaceAround) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = "Nombre: $nombre",
                    style = TextStyle(fontWeight = FontWeight.Bold)
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Edad: $edad",
                    style = TextStyle(fontWeight = FontWeight.Bold)
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "IMC: $imc",
                    style = TextStyle(fontWeight = FontWeight.Bold)
                )
                Button(onClick = { navController.navigate("DetailView2/$id") }) {
                Text("Editar")

}
            }
            Column(modifier = Modifier.padding(30.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.End
            ) {
                    if(genero=="Hombre"){
                        Image(painter = painterResource(id = R.drawable.baseline_male_24),
                        contentDescription = "Test Imagen",
                        modifier = Modifier.size(40.dp))
                    }
                    else{
                        Image(painter = painterResource(id = R.drawable.baseline_female_24),
                            contentDescription = "Test",
                            modifier = Modifier.size(40.dp))
                    }
                Spacer(modifier = Modifier.height(20.dp))
                if(imc.toDouble()<18.5){
                    Text(
                        text = "Bajo Peso",
                        style = TextStyle(fontWeight = FontWeight.Bold)
                    )
                }
                if(imc.toDouble()>=18.5 && imc.toDouble()<25){
                    Text(
                        text = "Peso Normal",
                        style = TextStyle(fontWeight = FontWeight.Bold)
                    )
                }
                if(imc.toDouble()>=25 && imc.toDouble()<30){
                    Text(
                        text = "Sobrepeso",
                        style = TextStyle(fontWeight = FontWeight.Bold)
                    )
                }
                if(imc.toDouble()>=30 && imc.toDouble()<35){
                    Text(
                        text = "Obesidad I",
                        style = TextStyle(fontWeight = FontWeight.Bold)
                    )
                }
                if(imc.toDouble()>=35 && imc.toDouble()<40){
                    Text(
                        text = "Obesidad II",
                        style = TextStyle(fontWeight = FontWeight.Bold)
                    )
                }
                if(imc.toDouble()>40){
                    Text(
                        text = "Obesidad III",
                        style = TextStyle(fontWeight = FontWeight.Bold)
                    )
                }

            }
        }
    }
}


