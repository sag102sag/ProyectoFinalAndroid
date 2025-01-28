package com.example.proyectofinalandroid.modelo

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Parque(
    @SerialName(value="id")
    val id: Int,
    @SerialName(value="nombre")
    val nombre: String="",
    @SerialName(value="extension")
    val extension: Double=0.0,
    @SerialName(value="especies")
    val especies: List<Especie> = listOf()

)