package com.example.tiendaparcial.Screen

import androidx.compose.foundation.layout.*
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Save
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.tiendaparcial.Modelo.Producto
import com.example.tiendaparcial.productos

@Composable
fun RegistroProductoScreen(navController: NavController) {
    var nombre by remember { mutableStateOf("") }
    var precio by remember { mutableStateOf("") }
    var descripcion by remember { mutableStateOf("") }
    var imagenUrl by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar (title = { Text("Agregar Producto") })
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
                .fillMaxSize()
        ) {
            OutlinedTextField(
                value = nombre,
                onValueChange = { nombre = it },
                label = { Text("Nombre") },
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = precio,
                onValueChange = { precio = it },
                label = { Text("Precio") },
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = descripcion,
                onValueChange = { descripcion = it },
                label = { Text("Descripción") },
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = imagenUrl,
                onValueChange = { imagenUrl = it },
                label = { Text("URL de Imagen") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    if (nombre.isNotBlank() && precio.isNotBlank() && descripcion.isNotBlank() && imagenUrl.isNotBlank()) {
                        val nuevoProducto = Producto(
                            id = productos.size + 1,
                            nombre = nombre,
                            precio = precio.toDoubleOrNull() ?: 0.0,
                            descripcion = descripcion,
                            imagenUrl = imagenUrl
                        )
                        productos.add(nuevoProducto)
                        navController.popBackStack()
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Icon(Icons.Default.Save, contentDescription = "Guardar")
                Spacer(modifier = Modifier.width(8.dp))
                Text("Guardar Producto")
            }

            OutlinedButton(
                onClick = { navController.popBackStack() },
                modifier = Modifier.fillMaxWidth()
            ) {
                Icon(Icons.Default.ArrowBack, contentDescription = "Cancelar")
                Spacer(modifier = Modifier.width(8.dp))
                Text("Cancelar")
            }
        }
    }
}
