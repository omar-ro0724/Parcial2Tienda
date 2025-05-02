package com.example.tiendaparcial.Screen

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.ShoppingCart

import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.tiendaparcial.carrito

@Composable
fun CarritoScreen(navController: NavController) {
    val context = LocalContext.current
    val total = remember { derivedStateOf { carrito.sumOf { it.precio } } }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Icon(
                imageVector = Icons.Default.ShoppingCart,
                contentDescription = "Icono Carrito",
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(28.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text("Carrito de Compras", style = MaterialTheme.typography.titleLarge)
        }

        Spacer(modifier = Modifier.height(12.dp))

        LazyColumn(modifier = Modifier.weight(1f)) {
            items(carrito) { producto ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(producto.nombre, style = MaterialTheme.typography.bodyLarge)
                    Text("$${producto.precio}", style = MaterialTheme.typography.bodyLarge)
                }
            }
        }

        Divider(modifier = Modifier.padding(vertical = 8.dp))

        Text(
            "Total a pagar: $${"%.2f".format(total.value)}",
            style = MaterialTheme.typography.titleMedium
        )

        Spacer(modifier = Modifier.height(12.dp))

        Button(
            onClick = {
                Toast.makeText(context, "¡Compra finalizada!", Toast.LENGTH_SHORT).show()
                carrito.clear()
                navController.popBackStack()
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Icon(
                imageVector = Icons.Default.CheckCircle,
                contentDescription = "Finalizar",
                modifier = Modifier.size(20.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text("Finalizar Compra")
        }

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedButton(
            onClick = { navController.popBackStack() },
            modifier = Modifier.fillMaxWidth()
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Volver",
                modifier = Modifier.size(20.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text("Volver al Catálogo")
        }
    }
}
