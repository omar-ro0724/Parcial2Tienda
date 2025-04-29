package com.example.tiendaparcial.Navegacion

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.tiendaparcial.Screen.CarritoScreen
import com.example.tiendaparcial.Screen.CatalogoScreen
import com.example.tiendaparcial.Screen.DetalleProductoScreen
import com.example.tiendaparcial.Screen.RegistroProductoScreen


@Composable
fun NavegacionGrafica() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "catalogo") {
        composable("catalogo") { CatalogoScreen(navController) }
        composable("registro") { RegistroProductoScreen(navController) }
        composable("detalle/{productoId}") { backStackEntry ->
            val productoId = backStackEntry.arguments?.getString("productoId")?.toIntOrNull()
            DetalleProductoScreen(navController, productoId)
        }
        composable("carrito") { CarritoScreen(navController) }
    }
}