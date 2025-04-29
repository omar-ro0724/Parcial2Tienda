package com.example.tiendaparcial.Screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.compose.AsyncImagePainter
import com.example.tiendaparcial.carrito
import com.example.tiendaparcial.productos

@Composable
fun DetalleProductoScreen(navController: NavController, productoId: Int?) {
    val producto = productos.find { it.id == productoId }

    if (producto == null) {
        Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
            Text("Producto no encontrado.", color = Color.Red)
            Button(onClick = { navController.popBackStack() }) {
                Text("Volver")
            }
        }
    } else {
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)) {

            var imageState by remember { mutableStateOf<AsyncImagePainter.State?>(null) }

            AsyncImage(
                model = producto.imagenUrl,
                contentDescription = producto.nombre,
                onState = { imageState = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            )

            if (imageState is AsyncImagePainter.State.Error) {
                Text("Imagen no disponible", color = Color.Gray)
            }

            Spacer(modifier = Modifier.height(16.dp))
            Text(text = producto.nombre, style = MaterialTheme.typography.titleLarge)
            Text(text = "Precio: $${producto.precio}", style = MaterialTheme.typography.bodyLarge)
            Text(text = producto.descripcion, style = MaterialTheme.typography.bodyMedium)

            Spacer(modifier = Modifier.height(16.dp))

            Button(onClick = {
                carrito.add(producto)
                navController.popBackStack()
            }) {
                Text("Agregar al Carrito")
            }

            Spacer(modifier = Modifier.height(8.dp))

            Button(onClick = { navController.popBackStack() }) {
                Text("Volver al Catálogo")
            }
        }
    }
}