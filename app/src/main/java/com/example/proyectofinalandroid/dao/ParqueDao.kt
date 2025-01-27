package com.example.proyectofinalandroid.dao

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.proyectofinalandroid.modelo.EspecieDB
import com.example.proyectofinalandroid.modelo.ParqueDB

interface ParqueDao {
    @Query("SELECT * from Especies WHERE id = :id")
    suspend fun obtenerEspecie(id: Int): EspecieDB

    @Query("SELECT * from Especies ORDER BY nombre ASC")
    suspend fun obtenerTodasEspecie(): List<EspecieDB>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertarEspecie(especieDB: EspecieDB)

    @Update
    suspend fun actualizarEspecie(especieDB: EspecieDB)

    @Delete
    suspend fun eliminarEspecie(especieDB: EspecieDB)

    @Query("SELECT * from Parques WHERE id = :id")
    suspend fun obtenerParque(id: Int): ParqueDB

    @Query("SELECT * from Parques ORDER BY nombre ASC")
    suspend fun obtenerTodosParques(): List<ParqueDB>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertarParque(parqueDB: ParqueDB)

    @Update
    suspend fun actualizarParque(parqueDB: ParqueDB)

    @Delete
    suspend fun eliminarParque(parqueDB: ParqueDB)
}