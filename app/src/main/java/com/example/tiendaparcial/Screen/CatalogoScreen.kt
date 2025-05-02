package com.example.tiendaparcial.Screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.tiendaparcial.carrito
import com.example.tiendaparcial.productos

@Composable
fun CatalogoScreen(navController: NavController) {
    val total = remember { derivedStateOf { carrito.sumOf { it.precio } } }

    Scaffold(
        topBar = {
            TopAppBar (
                title = { Text("Catálogo de Productos") },
                actions = {
                    IconButton(onClick = { navController.navigate("carrito") }) {
                        Icon(Icons.Default.ShoppingCart, contentDescription = "Carrito")
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { navController.navigate("registro") }) {
                Icon(Icons.Default.Add, contentDescription = "Agregar Producto")
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
                .fillMaxSize()
        ) {
            LazyColumn(modifier = Modifier.weight(1f)) {
                items(productos, key = { it.id }) { producto ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp),
                        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            AsyncImage(
                                model = producto.imagenUrl,
                                contentDescription = producto.nombre,
                                modifier = Modifier.size(80.dp)
                            )
                            Spacer(modifier = Modifier.width(16.dp))
                            Column(
                                modifier = Modifier.weight(1f).clickable {
                                    navController.navigate("detalle/${producto.id}")
                                }
                            ) {
                                Text(producto.nombre, style = MaterialTheme.typography.titleMedium)
                                Text("$${producto.precio}", style = MaterialTheme.typography.bodyLarge)
                            }
                            IconButton(onClick = {
                                productos.remove(producto)
                            }) {
                                Icon(
                                    imageVector = Icons.Default.Delete,
                                    contentDescription = "Eliminar",
                                    tint = MaterialTheme.colorScheme.error
                                )
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(8.dp))
            Text(
                "Total en carrito: $${"%.2f".format(total.value)}",
                style = MaterialTheme.typography.titleMedium
            )
        }
    }
}
