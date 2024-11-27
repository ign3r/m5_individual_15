package com.example.m5individual15.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicText
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.m5individual15.DataStoreManager
import com.example.m5individual15.R
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch

//@OptIn(ExperimentalPagerApi::class)
//@Composable
//fun IntroScreen(onFinish: () -> Unit) {
//    val pagerState = rememberPagerState()
//    val scope = rememberCoroutineScope()
//
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .padding(16.dp),
//        verticalArrangement = Arrangement.SpaceBetween,
//        horizontalAlignment = Alignment.CenterHorizontally
//    ) {
//        // Pager
//        HorizontalPager(
//            count = 3,
//            state = pagerState,
//            modifier = Modifier.weight(1f)
//        ) { page ->
//            IntroPage(
//                title = when (page) {
//                    0 -> "Bienvenido a la App"
//                    1 -> "Explora nuestras funciones"
//                    else -> "¡Comencemos!"
//                },
//                description = when (page) {
//                    0 -> "Aquí puedes aprender cómo usar nuestra app."
//                    1 -> "Descubre todas las opciones y herramientas disponibles."
//                    else -> "Listo para comenzar tu experiencia."
//                }
//            )
//        }
//
//        // Botón de navegación o finalización
//        if (pagerState.currentPage == 2) {
//            Button(
//                onClick = onFinish,
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(vertical = 16.dp)
//            ) {
//                Text(text = "Ir a la aplicación")
//            }
//        } else {
//            Row(
//                horizontalArrangement = Arrangement.SpaceBetween,
//                verticalAlignment = Alignment.CenterVertically,
//                modifier = Modifier.fillMaxWidth()
//            ) {
//                TextButton(
//                    onClick = { scope.launch { pagerState.animateScrollToPage(2) } }
//                ) {
//                    Text("Saltar")
//                }
//                IconButton(
//                    onClick = { scope.launch { pagerState.animateScrollToPage(pagerState.currentPage + 1) } }
//                ) {
//                    Icon(
//                        imageVector = androidx.compose.material.icons.Icons.Filled.ArrowForward,
//                        contentDescription = "Siguiente"
//                    )
//                }
//            }
//        }
//    }
//}
//
//@Composable
//fun IntroPage(title: String, description: String) {
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .padding(horizontal = 16.dp),
//        verticalArrangement = Arrangement.Center,
//        horizontalAlignment = Alignment.CenterHorizontally
//    ) {
//        BasicText(
//            text = title,
//            style = MaterialTheme.typography.headlineMedium
//        )
//        Spacer(modifier = Modifier.height(16.dp))
//        BasicText(
//            text = description,
//            style = MaterialTheme.typography.bodyMedium
//        )
//    }
//}
@Composable
fun IntroScreen(onFinish: () -> Unit, dataStoreManager: DataStoreManager) {
    val scope = rememberCoroutineScope()

    val images: List<Int> = listOf(
        R.drawable.paciente,
        R.drawable.imagen2,
        R.drawable.imagen3
    )

    val titles: List<String> = listOf(
        "Listado de Pacientes",
        "Fácil de Usar",
        "Calculadora de IMC"
    )

    val pages = listOf(
        "Crea un registro de todos tus pacientes y sus datos mas importantes",
        "Registro intuitivo y rapido",
        "Cuentas con una calculadora de indice de masa corporal para registrar rapidamente los datos de tus pacientes"
    )

    val pagerState = rememberPagerState()

    Column(
        Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center
    ) {
        HorizontalPager(
            count = pages.size,
            state = pagerState,
            modifier = Modifier.weight(1f)
        ) { page ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(10.dp),
                verticalArrangement = Arrangement.SpaceEvenly, // Distribuye uniformemente
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Título principal
                Text(
                    text = titles[page],
                    style = TextStyle(fontWeight = FontWeight.Bold),
                    fontSize = 40.sp
                )
                Image(
                    painter = painterResource(id = images[page]),
                    contentDescription = "fdf",
                    modifier = Modifier.size(300.dp))

                // Espaciador entre título y descripción
                Spacer(modifier = Modifier.height(20.dp))

                // Descripción
                Text(
                    text = pages[page],
                    fontSize = 20.sp

                )

                // Espaciador para ocupar el espacio del botón en páginas anteriores
                Spacer(modifier = Modifier.height(30.dp))

                // Botón visible solo en la última página
                if (page == pages.lastIndex) {
                    Button(
                        onClick = {
                            scope.launch {
                                 dataStoreManager.setOnboardingShown() // Marca como visto
                                onFinish()
                            }
                        },
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    ) {
                        Text("Comenzar")
                    }
                } else {
                    // Espaciador que ocupa el lugar del botón para mantener la altura
                    Spacer(modifier = Modifier.height(48.dp)) // Altura aproximada del botón
                }
            }
        }
    }
}




