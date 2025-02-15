package com.example.proyectofinalandroid.datos

import com.example.proyectofinalandroid.conexion.ParquesServicioApi
import com.example.proyectofinalandroid.modelo.Especie
import com.example.proyectofinalandroid.modelo.Parque
import retrofit2.Response

interface ParqueRepositorio {
    suspend fun obtenerEspecies(): List<Especie>
    suspend fun obtenerEspecie(id: Int): Especie
    suspend fun insertarEspecie(especie: Especie): Especie
    suspend fun actualizarEspecie(id: Int, especie: Especie): Especie
    suspend fun eliminarEspecie(id: Int): Response<Unit>

    suspend fun obtenerParques(): List<Parque>
    suspend fun obtenerParque(id: Int): Parque
    suspend fun insertarParque(parque: Parque): Parque
    suspend fun actualizarParque(id: Int, parque: Parque): Parque
    suspend fun eliminarParque(id: Int): Response<Unit>
}

class ConexionParqueRepositorioServer(
    private val parquesServicioApi: ParquesServicioApi
): ParqueRepositorio
{
    override suspend fun obtenerEspecies(): List<Especie> = parquesServicioApi.obtenerEspecies()
    override suspend fun obtenerEspecie(id: Int): Especie = parquesServicioApi.obtenerEspecie(id)
    override suspend fun insertarEspecie(especie: Especie): Especie = parquesServicioApi.insertarEspecie(especie)
    override suspend fun actualizarEspecie(id: Int, especie: Especie): Especie = parquesServicioApi.actualizarEspecie(id, especie)
    override suspend fun eliminarEspecie(id: Int): Response<Unit> = parquesServicioApi.eliminarEspecie(id)

    override suspend fun obtenerParques(): List<Parque> = parquesServicioApi.obtenerParques()
    override suspend fun obtenerParque(id: Int): Parque = parquesServicioApi.obtenerParque(id)
    override suspend fun insertarParque(parque: Parque): Parque = parquesServicioApi.insertarParque(parque)
    override suspend fun actualizarParque(id: Int, parque: Parque): Parque = parquesServicioApi.actualizarParque(id, parque)
    override suspend fun eliminarParque(id: Int): Response<Unit> = parquesServicioApi.eliminarParque(id)
}