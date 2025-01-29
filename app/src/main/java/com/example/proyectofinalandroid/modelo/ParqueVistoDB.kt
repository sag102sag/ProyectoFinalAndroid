package com.example.proyectofinalandroid.modelo

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Parques")
data class ParqueVistoDB (
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val nombre: String="",
    val comunidadAutonoma: Int=0,
    val extension: Double=0.0,
    val organismoID: Int=0,
    val tipoParqueID: Int=0,
    val fechaVisto: String="",
    val comentario: String="",
    val puntuacion: Double=0.0,
    val favorito: Boolean=false
)