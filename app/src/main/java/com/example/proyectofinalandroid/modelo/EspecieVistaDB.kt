package com.example.proyectofinalandroid.modelo

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "EspeciesVistas")
data class EspecieVistaDB (
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val nombre: String="",
    val descripcion: String="",
    val tipo: String="",
    val fechaVisto: String="",
    val comentario: String="",
    val cantidadVista: Int=0,
    val favorito: Boolean=false
)
