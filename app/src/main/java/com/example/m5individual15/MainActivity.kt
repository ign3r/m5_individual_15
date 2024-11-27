package com.example.m5individual15

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.m5individual15.navigation.NavManager
import com.example.m5individual15.ui.theme.M5Individual15Theme
import com.example.m5individual15.viewmodel.IMCViewModel
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Instancia de ViewModel
        val viewModel: IMCViewModel by viewModels()

        // Instancia de DataStoreManager
        val dataStoreManager = DataStoreManager(this)

        // Configuración del contenido
        setContent {
            M5Individual15Theme {
                // Pasamos DataStoreManager y ViewModel a NavManager
                NavManager(
                    viewModel = viewModel,
                    dataStoreManager = dataStoreManager,
                    context = this
                )
            }
        }
    }
}


