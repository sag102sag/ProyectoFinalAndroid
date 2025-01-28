package com.example.proyectofinalandroid.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.listSaver
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proyectofinalandroid.datos.ParqueRepositorio
import com.example.proyectofinalandroid.datos.ParqueRepositorioDB
import com.example.proyectofinalandroid.modelo.Especie
import com.example.proyectofinalandroid.modelo.EspecieVistaDB
import com.example.proyectofinalandroid.modelo.Parque
import com.example.proyectofinalandroid.modelo.ParqueVistoDB
import kotlinx.coroutines.launch

sealed interface ParquesUIState{
    data class ObtenerExitoEspecies (val especies: List<Especie>): ParquesUIState
    data class ObtenerExitoParques (val parques: List<Parque>): ParquesUIState
    data class ObtenerExitoEspeciesVistas (val especiesVistas: List<EspecieVistaDB>): ParquesUIState
    data class ObtenerExitoParquesVistos (val parquesVistos: List<ParqueVistoDB>): ParquesUIState

    data class ObtenerExitoEspeciePorID (val especie: Especie): ParquesUIState
    data class ObtenerExitoParquePorID (val parque: Parque): ParquesUIState
    data class ObtenerExitoEspecieDBPorID (val especie: EspecieVistaDB): ParquesUIState
    data class ObtenerExitoParqueDBPorID (val parque: ParqueVistoDB): ParquesUIState

    object CrearExito: ParquesUIState
    object ActualizarExito: ParquesUIState
    object EliminarExito: ParquesUIState
    object Error: ParquesUIState
    object Cargando: ParquesUIState
}

class ParquesViewModel(
    private val parqueRepositorio: ParqueRepositorio,
    private val parqueRepositorioDB: ParqueRepositorioDB
): ViewModel() {

    var parquesUIState: ParquesUIState by mutableStateOf(ParquesUIState.Cargando)
        private set

    var especiePulsada: Especie by mutableStateOf(Especie(0,))
        private set

    var parquePulsado: Parque by mutableStateOf(Parque(0,))
        private set

    var especieVistaDBPulsada: EspecieVistaDB by mutableStateOf(EspecieVistaDB())
        private set

    var parqueVistoDBPulsado: ParqueVistoDB by mutableStateOf(ParqueVistoDB())
        private set

    init {
        obtenerParques()
        obtenerEspecies()
    }

    // ------------------------------- OBTENCION DE PARQUES -------------------------------

    // SERVER
    fun obtenerParques()
    {
        viewModelScope.launch {
            parquesUIState = try {
                val lista = parqueRepositorio.obtenerParques()
                ParquesUIState.ObtenerExitoParques(lista)
            }catch (e: Exception) {
                ParquesUIState.Error
            }
        }
    }

    fun obtenerParque(id: Int)
    {
        viewModelScope.launch {
            parquesUIState = try {
                val parque = parqueRepositorio.obtenerParque(id)
                parquePulsado = parque
                ParquesUIState.ObtenerExitoParquePorID(parque)
            } catch (e: Exception) {
                ParquesUIState.Error
            }
        }
    }

    // INTERNOS
    fun obtenerParquesVistosDB()
    {
        viewModelScope.launch {
            parquesUIState = try {
                val lista = parqueRepositorioDB.obtenerTodosParques()
                ParquesUIState.ObtenerExitoParquesVistos(lista)
            }catch (e: Exception) {
                ParquesUIState.Error
            }
        }
    }

    fun obtenerParqueVisto(id: Int)
    {
        viewModelScope.launch {
            parquesUIState = try {
                val parque = parqueRepositorioDB.obtenerParque(id)
                parqueVistoDBPulsado = parque
                ParquesUIState.ObtenerExitoParqueDBPorID(parque)
            } catch (e: Exception) {
                ParquesUIState.Error
            }
        }
    }

    // ------------------------------- OBTENCION DE ESPECIES -------------------------------

    // SERVER
    fun obtenerEspecies()
    {
        viewModelScope.launch {
            parquesUIState = try {
                val lista = parqueRepositorio.obtenerEspecies()
                ParquesUIState.ObtenerExitoEspecies(lista)
            }catch (e: Exception) {
                ParquesUIState.Error
            }
        }
    }

    fun obtenerEspecie(id: Int)
    {
        viewModelScope.launch {
            parquesUIState = try {
                val especie = parqueRepositorio.obtenerEspecie(id)
                especiePulsada = especie
                ParquesUIState.ObtenerExitoEspeciePorID(especie)
            } catch (e: Exception) {
                ParquesUIState.Error
            }
        }
    }

    // INTERNOS
    fun obtenerEspeciesVistas()
    {
        viewModelScope.launch {
            parquesUIState = try {
                val lista = parqueRepositorioDB.obtenerTodasEspecies()
                ParquesUIState.ObtenerExitoEspeciesVistas(lista)
            }catch (e: Exception) {
                ParquesUIState.Error
            }
        }
    }

    fun obtenerEspecieVista(id: Int)
    {
        viewModelScope.launch {
            parquesUIState = try {
                val especieVistaDB = parqueRepositorioDB.obtenerEspecie(id)
                especieVistaDBPulsada = especieVistaDB
                ParquesUIState.ObtenerExitoEspecieDBPorID(especieVistaDB)
            } catch (e: Exception) {
                ParquesUIState.Error
            }
        }
    }

    fun insertarParque(parque: Parque) {
        viewModelScope.launch {
            parquesUIState = try {
                parqueRepositorio.insertarParque(parque)
                ParquesUIState.CrearExito
            } catch (e: Exception) {
                ParquesUIState.Error
            }
        }
    }

    fun actualizarParque(id: Int, parque: Parque)
    {
        viewModelScope.launch {
            parquesUIState = try {
                parqueRepositorio.actualizarParque(id, parque)
                ParquesUIState.ActualizarExito
            } catch (e: Exception) {
                ParquesUIState.Error
            }
        }
    }
}