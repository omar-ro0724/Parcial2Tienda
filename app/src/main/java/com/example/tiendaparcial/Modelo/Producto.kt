package com.example.tiendaparcial.Modelo

data class Producto(
    val id: Int,
    val nombre: String,
    val precio: Double,
    val descripcion: String,
    val imagenUrl: String
)