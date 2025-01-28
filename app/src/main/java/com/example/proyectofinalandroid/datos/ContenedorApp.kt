package com.example.proyectofinalandroid.datos

import android.content.Context
import com.example.proyectofinalandroid.conexion.ParquesBaseDatos
import com.example.proyectofinalandroid.conexion.ParquesServicioApi
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

interface ContenedorApp {
    val parqueRepositorio: ParqueRepositorio
    val parqueRepositorioDB: ParqueRepositorioDB
}

class ParquesContenedorApp(private val context: Context) : ContenedorApp {

    private val baseUrl = "http://10.0.2.2:8080"

    private val json = Json { ignoreUnknownKeys = true
    coerceInputValues = true}


    private val retrofit = Retrofit.Builder()
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(baseUrl)
        .build()

    private val servicioRetrofit: ParquesServicioApi by lazy{
        retrofit.create(ParquesServicioApi::class.java)
    }
    // Server
    override val parqueRepositorio: ParqueRepositorio by lazy {
        ConexionParqueRepositorioServer(servicioRetrofit)
    }
    // Local
    override val parqueRepositorioDB: ParqueRepositorioDB by lazy {
        ConexionParqueRepositorio(ParquesBaseDatos.obtenerBaseDatos(context).parqueDao())
    }
}