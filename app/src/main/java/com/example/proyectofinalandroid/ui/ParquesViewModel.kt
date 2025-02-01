package com.example.proyectofinalandroid.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.listSaver
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.proyectofinalandroid.ParquesAplicacion
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

    fun actualizarParquePulsado(parque: Parque)
    {
        parquePulsado=parque
    }

    fun actualizarEspeciePulsada(especie: Especie)
    {
        especiePulsada=especie
    }

    fun resetUIState()
    {
        parquesUIState = ParquesUIState.Cargando
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

    // ------------------------------------------ CRUDS --------------------------------------------

    // PARQUES ---------------------------------------------------------------

    fun insertarParque(parque: Parque)
    {
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

    fun eliminarParque(id: Int)
    {
        viewModelScope.launch {
            parquesUIState = try {
                parqueRepositorio.eliminarParque(id)
                ParquesUIState.EliminarExito
            } catch (e: Exception) {
                ParquesUIState.Error
            }
        }
    }

    fun insertarParqueBD(parqueVistoDB: ParqueVistoDB)
    {
        viewModelScope.launch {
            parquesUIState = try {
                parqueRepositorioDB.insertarParque(parqueVistoDB)
                ParquesUIState.CrearExito
            } catch (e: Exception) {
                ParquesUIState.Error
            }
        }
    }

    fun actualizarParqueBD(parqueVistoDB: ParqueVistoDB)
    {
        viewModelScope.launch {
            parquesUIState = try {
                parqueRepositorioDB.actualizarParque(parqueVistoDB)
                ParquesUIState.ActualizarExito
            } catch (e: Exception) {
                ParquesUIState.Error
            }
        }
    }

    fun eliminarParqueBD(parqueVistoDB: ParqueVistoDB)
    {
        viewModelScope.launch {
            parquesUIState = try {
                parqueRepositorioDB.eliminarParque(parqueVistoDB)
                ParquesUIState.EliminarExito
            } catch (e: Exception) {
                ParquesUIState.Error
            }
        }
    }

    // ESPECIES ---------------------------------------------------------------

    fun insertarEspecie(especie: Especie)
    {
        viewModelScope.launch {
            parquesUIState = try {
                parqueRepositorio.insertarEspecie(especie)
                ParquesUIState.CrearExito
            } catch (e: Exception) {
                ParquesUIState.Error
            }
        }
    }

    fun actualizarEspecie(id: Int, especie: Especie)
    {
        viewModelScope.launch {
            parquesUIState = try {
                parqueRepositorio.actualizarEspecie(id, especie)
                ParquesUIState.ActualizarExito
            } catch (e: Exception) {
                ParquesUIState.Error
            }
        }
    }

    fun eliminarEspecie(id: Int)
    {
        viewModelScope.launch {
            parquesUIState = try {
                parqueRepositorio.eliminarEspecie(id)
                ParquesUIState.EliminarExito
            } catch (e: Exception) {
                ParquesUIState.Error
            }
        }
    }

    fun insertarEspecieBD(especieVistaDB: EspecieVistaDB)
    {
        viewModelScope.launch {
            parquesUIState = try {
                parqueRepositorioDB.insertarEspecie(especieVistaDB)
                ParquesUIState.CrearExito
            } catch (e: Exception) {
                ParquesUIState.Error
            }
        }
    }

    fun actualizarEspecieBD(especieVistaDB: EspecieVistaDB)
    {
        viewModelScope.launch {
            parquesUIState = try {
                parqueRepositorioDB.actualizarEspecie(especieVistaDB)
                ParquesUIState.ActualizarExito
            } catch (e: Exception) {
                ParquesUIState.Error
            }
        }
    }

    fun eliminarEspecie(especieVistaDB: EspecieVistaDB)
    {
        viewModelScope.launch {
            parquesUIState = try {
                parqueRepositorioDB.eliminarEspecie(especieVistaDB)
                ParquesUIState.EliminarExito
            } catch (e: Exception) {
                ParquesUIState.Error
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val aplicacion = (this[APPLICATION_KEY] as ParquesAplicacion)
                val parqueRepositorio = aplicacion.contenedor.parqueRepositorio
                val parqueRepositorioDB = aplicacion.contenedor.parqueRepositorioDB
                ParquesViewModel(
                    parqueRepositorio = parqueRepositorio,
                    parqueRepositorioDB = parqueRepositorioDB
                )
            }
        }
    }
}