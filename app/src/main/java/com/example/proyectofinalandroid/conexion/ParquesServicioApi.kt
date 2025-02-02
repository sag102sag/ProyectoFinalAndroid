package com.example.proyectofinalandroid.conexion

import com.example.proyectofinalandroid.modelo.Especie
import com.example.proyectofinalandroid.modelo.Parque
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ParquesServicioApi {
    @GET("especies")
    suspend fun obtenerEspecies(): List<Especie>

    @GET("especies/{id}")
    suspend fun obtenerEspecie(
        @Path("id") id: Int
    ): Especie

    @POST("especies")
    suspend fun insertarEspecie(
        @Body especie: Especie
    ): Especie

    @PUT("especies/{id}")
    suspend fun actualizarEspecie(
        @Path("id") id:Int,
        @Body especie: Especie
    ): Especie

    @DELETE("especies/{id}")
    suspend fun eliminarEspecie(
        @Path("id") id: Int
    ): Response<Unit>

    @GET("parques")
    suspend fun obtenerParques(): List<Parque>

    @GET("parques/{id}")
    suspend fun obtenerParque(
        @Path("id") id: Int
    ): Parque

    @POST("parques")
    suspend fun insertarParque(
        @Body parque: Parque
    ): Parque

    @PUT("parques/{id}")
    suspend fun actualizarParque(
        @Path("id") id:Int,
        @Body parque: Parque
    ): Parque

    @DELETE("parques/{id}")
    suspend fun eliminarParque(
        @Path("id") id: Int
    ): Response<Unit>
}