package com.example.proyectofinalandroid.modelo

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Parque(
    @SerialName(value="id")
    val id: Int,
    @SerialName(value="nombre")
    val nombre: String="",
    @SerialName(value="descripcion")
    val descripcion: String="",
    @SerialName(value="tipo")
    val tipo: String=""
)