package com.example.proyectofinalandroid.datos

import com.example.proyectofinalandroid.dao.ParqueDao
import com.example.proyectofinalandroid.modelo.EspecieVistaDB
import com.example.proyectofinalandroid.modelo.ParqueVistoDB

interface ParqueRepositorioDB {
    suspend fun obtenerEspecie(id: Int): EspecieVistaDB
    suspend fun obtenerTodasEspecies(): List<EspecieVistaDB>
    suspend fun insertarEspecie(especieVistaDB: EspecieVistaDB)
    suspend fun actualizarEspecie(especieVistaDB: EspecieVistaDB)
    suspend fun eliminarEspecie(especieVistaDB: EspecieVistaDB)
    suspend fun obtenerParque(id: Int): ParqueVistoDB
    suspend fun obtenerTodosParques(): List<ParqueVistoDB>
    suspend fun insertarParque(parqueVistoDB: ParqueVistoDB)
    suspend fun actualizarParque(parqueVistoDB: ParqueVistoDB)
    suspend fun eliminarParque(parqueVistoDB: ParqueVistoDB)
}

class ConexionParqueRepositorio(
    private val parqueDao: ParqueDao
): ParqueRepositorioDB {
    override suspend fun obtenerEspecie(id: Int): EspecieVistaDB = parqueDao.obtenerEspecie(id)
    override suspend fun obtenerTodasEspecies(): List<EspecieVistaDB> = parqueDao.obtenerTodasEspecie()
    override suspend fun insertarEspecie(especieVistaDB: EspecieVistaDB) = parqueDao.insertarEspecie(especieVistaDB)
    override suspend fun actualizarEspecie(especieVistaDB: EspecieVistaDB) = parqueDao.actualizarEspecie(especieVistaDB)
    override suspend fun eliminarEspecie(especieVistaDB: EspecieVistaDB) = parqueDao.eliminarEspecie(especieVistaDB)

    override suspend fun obtenerParque(id: Int): ParqueVistoDB = parqueDao.obtenerParque(id)
    override suspend fun obtenerTodosParques(): List<ParqueVistoDB> = parqueDao.obtenerTodosParques()
    override suspend fun insertarParque(parqueVistoDB: ParqueVistoDB) = parqueDao.insertarParque(parqueVistoDB)
    override suspend fun actualizarParque(parqueVistoDB: ParqueVistoDB) = parqueDao.actualizarParque(parqueVistoDB)
    override suspend fun eliminarParque(parqueVistoDB: ParqueVistoDB) = parqueDao.eliminarParque(parqueVistoDB)
}