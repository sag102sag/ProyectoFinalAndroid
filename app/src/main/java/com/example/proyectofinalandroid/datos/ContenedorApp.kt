package com.example.proyectofinalandroid.datos

import android.content.Context
import com.example.proyectofinalandroid.conexion.ParquesBaseDatos
import com.example.proyectofinalandroid.conexion.ParquesServicioApi
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

interface ContenedorApp {
    val parqueRepositorio: ParqueRepositorioDB
}

class ParquesContenedorApp(private val context: Context) : ContenedorApp {

    override val parqueRepositorio: ParqueRepositorioDB by lazy {
        ConexionParqueRepositorio(ParquesBaseDatos.obtenerBaseDatos(context).parqueDao())
    }
    private val baseUrl = "http://10.0.2.2:3000"

    private val json = Json { ignoreUnknownKeys = true }

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(baseUrl)
        .build()

    private val servicioRetrofit: ParquesServicioApi by lazy{
        retrofit.create(ParquesServicioApi::class.java)
    }

    override val parquesRepositorio: ParqueRepositorio by lazy {
        ConexionParqueRepositorioServer(servicioRetrofit)
    }
}