package com.example.tiendaparcial.Screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.compose.AsyncImagePainter
import com.example.tiendaparcial.carrito
import com.example.tiendaparcial.productos

@Composable
fun CatalogoScreen(navController: NavController) {
    val total = remember { derivedStateOf { carrito.sumOf { it.precio } } }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("Catálogo de Productos", style = MaterialTheme.typography.titleLarge)

        LazyColumn(modifier = Modifier.weight(1f)) {
            items(productos) { producto ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                        .clickable { navController.navigate("detalle/${producto.id}") }
                ) {
                    Row(modifier = Modifier.padding(8.dp)) {
                        AsyncImage(
                            model = producto.imagenUrl,
                            contentDescription = producto.nombre,
                            modifier = Modifier.size(80.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Column {
                            Text(producto.nombre)
                            Text("$${producto.precio}")
                        }
                    }
                }
            }
        }

        Text("Total en Carrito: $${"%.2f".format(total.value)}")

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
            Button(onClick = { navController.navigate("registro") }) {
                Text("Agregar Producto")
            }
            Button(onClick = { navController.navigate("carrito") }) {
                Text("Ir al Carrito")
            }
        }
    }
}
