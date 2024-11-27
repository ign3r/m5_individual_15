package com.example.m5individual15.navigation

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.m5individual15.DataStoreManager
import com.example.m5individual15.view.DetailView
import com.example.m5individual15.view.DetailView2
import com.example.m5individual15.view.HomeView
import com.example.m5individual15.view.IntroScreen
import com.example.m5individual15.view.SplashScreen
import com.example.m5individual15.viewmodel.IMCViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


//@Composable
//fun NavManager (viewModel: IMCViewModel,context: Context,dataStoreManager: DataStoreManager){
//    val navController= rememberNavController()
//    val context = LocalContext.current
//    val dataStore = DataStoreManager(context)
//    val store = dataStore.getBoarding.collectAsState(initial = true)
//    NavHost(navController, startDestination = if (store.value) "intro" else "Home" ) {
//        composable("Home") {
//            HomeView(viewModel, navController, context)
//        }
//        composable("DetailView") {
//            DetailView(viewModel, navController)
//        }
//        composable(
//            "DetailView2/{id}",
//            arguments = listOf(navArgument("id") { type = NavType.IntType })
//        ) { backStackEntry ->
////            DetailView2(id=id,viewModel,navController)
//            composable("intro") {
//                IntroScreen {
//
//                }
//                val id = backStackEntry.arguments?.getInt("id") ?: 0
//                DetailView2(id, viewModel, navController,)
//            }
//
//        }
//    }
//}
@Composable
fun NavManager(viewModel: IMCViewModel, context: Context, dataStoreManager: DataStoreManager) {
    val navController = rememberNavController()
    val store = dataStoreManager.shouldShowOnboarding.collectAsState(initial = true)

    NavHost(
        navController = navController,
        startDestination = "splash"
    ) {
        composable("splash") {
            SplashScreen {
                // Navegar al onboarding o a la pantalla principal según corresponda
                navController.navigate(if (store.value) "intro" else "Home") {
                    popUpTo("splash") { inclusive = true } // Elimina el Splash del back stack
                }
            }
        }
        composable("intro") {
            IntroScreen(
                onFinish = {
                    // Marca las instrucciones como vistas y navega a Home
                    navController.navigate("Home") {
                        popUpTo("intro") { inclusive = true }
                    }
                },
                dataStoreManager = dataStoreManager // Pasa DataStoreManager
            )
        }
        composable("Home") {
            HomeView(viewModel, navController, context)
        }
        composable("DetailView") {
            DetailView(viewModel, navController)
        }
        composable(
            "DetailView2/{id}",
            arguments = listOf(navArgument("id") { type = NavType.IntType })
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getInt("id") ?: 0
            DetailView2(id, viewModel, navController)
        }
    }
}

