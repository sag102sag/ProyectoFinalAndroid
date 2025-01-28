package com.example.proyectofinalandroid.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.proyectofinalandroid.modelo.EspecieVistaDB
import com.example.proyectofinalandroid.modelo.ParqueVistoDB

@Dao
interface ParqueDao {
    @Query("SELECT * from Especies WHERE id = :id")
    suspend fun obtenerEspecie(id: Int): EspecieVistaDB

    @Query("SELECT * from Especies ORDER BY nombre ASC")
    suspend fun obtenerTodasEspecie(): List<EspecieVistaDB>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertarEspecie(especieVistaDB: EspecieVistaDB)

    @Update
    suspend fun actualizarEspecie(especieVistaDB: EspecieVistaDB)

    @Delete
    suspend fun eliminarEspecie(especieVistaDB: EspecieVistaDB)

    @Query("SELECT * from Parques WHERE id = :id")
    suspend fun obtenerParque(id: Int): ParqueVistoDB

    @Query("SELECT * from Parques ORDER BY nombre ASC")
    suspend fun obtenerTodosParques(): List<ParqueVistoDB>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertarParque(parqueVistoDB: ParqueVistoDB)

    @Update
    suspend fun actualizarParque(parqueVistoDB: ParqueVistoDB)

    @Delete
    suspend fun eliminarParque(parqueVistoDB: ParqueVistoDB)
}