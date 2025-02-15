package com.example.proyectofinalandroid.modelo

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ParquesVistos")
data class ParqueVistoDB (
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val nombre: String="",
    val extension: Double=0.0,
    val fechaVisto: String="",
    val comentario: String="",
    val puntuacion: Double=0.0,
    val favorito: Boolean=false
)