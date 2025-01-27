package com.example.proyectofinalandroid.datos

import com.example.proyectofinalandroid.dao.ParqueDao
import com.example.proyectofinalandroid.modelo.EspecieDB
import com.example.proyectofinalandroid.modelo.ParqueDB

interface ParqueRepositorioDB {
    suspend fun obtenerEspecie(id: Int): EspecieDB
    suspend fun obtenerTodasEspecies(): List<EspecieDB>
    suspend fun insertarEspecie(especieDB: EspecieDB)
    suspend fun actualizarEspecie(especieDB: EspecieDB)
    suspend fun eliminarEspecie(especieDB: EspecieDB)
    suspend fun obtenerParque(id: Int): ParqueDB
    suspend fun obtenerTodosParques(): List<ParqueDB>
    suspend fun insertarParque(parqueDB: ParqueDB)
    suspend fun actualizarParque(parqueDB: ParqueDB)
    suspend fun eliminarParque(parqueDB: ParqueDB)
}

class ConexionParqueRepositorio(
    private val parqueDao: ParqueDao
): ParqueRepositorioDB {
    override suspend fun obtenerEspecie(id: Int): EspecieDB = parqueDao.obtenerEspecie(id)
    override suspend fun obtenerTodasEspecies(): List<EspecieDB> = parqueDao.obtenerTodasEspecie()
    override suspend fun insertarEspecie(especieDB: EspecieDB) = parqueDao.insertarEspecie(especieDB)
    override suspend fun actualizarEspecie(especieDB: EspecieDB) = parqueDao.actualizarEspecie(especieDB)
    override suspend fun eliminarEspecie(especieDB: EspecieDB) = parqueDao.eliminarEspecie(especieDB)
    override suspend fun obtenerParque(id: Int): ParqueDB = parqueDao.obtenerParque(id)
    override suspend fun obtenerTodosParques(): List<ParqueDB> = parqueDao.obtenerTodosParques()
    override suspend fun insertarParque(parqueDB: ParqueDB) = parqueDao.insertarParque(parqueDB)
    override suspend fun actualizarParque(parqueDB: ParqueDB) = parqueDao.actualizarParque(parqueDB)
    override suspend fun eliminarParque(parqueDB: ParqueDB) = parqueDao.eliminarParque(parqueDB)
}