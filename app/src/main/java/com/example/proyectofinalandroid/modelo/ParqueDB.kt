package com.example.proyectofinalandroid.modelo

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Parques")
data class ParqueDB (
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val nombre: String,
    val comunidadAutonoma: Int,
    val extension: Double,
    val organismoID: Int,
    val tipoParqueID: Int
)