package com.example.tiendaparcial.Screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.tiendaparcial.carrito

@Composable
fun CarritoScreen(navController: NavController) {
    val total = remember { derivedStateOf { carrito.sumOf { it.precio } } }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("Carrito de Compras", style = MaterialTheme.typography.titleLarge)

        LazyColumn(modifier = Modifier.weight(1f)) {
            items(carrito) { producto ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(producto.nombre)
                    Text("$${producto.precio}")
                }
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        Text("Total a pagar: $${"%.2f".format(total.value)}", style = MaterialTheme.typography.titleMedium)

        Spacer(modifier = Modifier.height(8.dp))

        Button(onClick = {
            carrito.clear()
            navController.popBackStack()

        }) {
            Text("Finalizar Compra")
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(onClick = { navController.popBackStack() }) {
            Text("Volver al Catálogo")
        }
    }
}