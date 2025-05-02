package com.example.tiendaparcial.Screen

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddShoppingCart
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.compose.AsyncImagePainter
import com.example.tiendaparcial.carrito
import com.example.tiendaparcial.productos
import android.widget.Toast
import androidx.compose.material.TopAppBar

@Composable
fun DetalleProductoScreen(navController: NavController, productoId: Int?) {
    val producto = productos.find { it.id == productoId }
    val context = LocalContext.current

    if (producto == null) {
        Column(
            modifier = Modifier.fillMaxSize().padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text("Producto no encontrado.", color = Color.Red, style = MaterialTheme.typography.titleLarge)
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedButton(onClick = { navController.popBackStack() }) {
                Icon(Icons.Default.ArrowBack, contentDescription = "Volver")
                Spacer(modifier = Modifier.width(8.dp))
                Text("Volver")
            }
        }
    } else {
        var cantidadText by remember { mutableStateOf("1") }
        var imageState by remember { mutableStateOf<AsyncImagePainter.State?>(null) }

        Scaffold(
            topBar = {
                TopAppBar (
                    title = { Text(producto.nombre) },
                    navigationIcon = {
                        IconButton(onClick = { navController.popBackStack() }) {
                            Icon(Icons.Default.ArrowBack, contentDescription = "Volver")
                        }
                    }
                )
            }
        ) { padding ->
            Column(
                modifier = Modifier
                    .padding(padding)
                    .padding(16.dp)
                    .fillMaxSize()
            ) {
                AsyncImage(
                    model = producto.imagenUrl,
                    contentDescription = producto.nombre,
                    onState = { imageState = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                )

                if (imageState is AsyncImagePainter.State.Error) {
                    Spacer(modifier = Modifier.height(8.dp))
                    Text("Imagen no disponible", color = Color.Gray)
                }

                Spacer(modifier = Modifier.height(16.dp))

                Text(text = producto.nombre, style = MaterialTheme.typography.titleLarge)
                Text(text = "Precio: $${producto.precio}", style = MaterialTheme.typography.bodyLarge)
                Text(text = producto.descripcion, style = MaterialTheme.typography.bodyMedium)

                Spacer(modifier = Modifier.height(24.dp))

                OutlinedTextField(
                    value = cantidadText,
                    onValueChange = { cantidadText = it },
                    label = { Text("Cantidad") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true
                )

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = {
                        val cantidad = cantidadText.toIntOrNull()
                        if (cantidad != null && cantidad > 0) {
                            repeat(cantidad) {
                                carrito.add(producto)
                            }
                            Toast.makeText(context, "Se agregaron $cantidad al carrito", Toast.LENGTH_SHORT).show()
                            navController.popBackStack()
                        } else {
                            Toast.makeText(context, "Ingresa una cantidad válida", Toast.LENGTH_SHORT).show()
                        }
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Icon(Icons.Default.AddShoppingCart, contentDescription = "Agregar al carrito")
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Agregar al Carrito")
                }
            }
        }
    }
}
