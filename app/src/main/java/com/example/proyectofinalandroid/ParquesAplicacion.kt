package com.example.proyectofinalandroid

import android.app.Application
import com.example.proyectofinalandroid.datos.ContenedorApp
import com.example.proyectofinalandroid.datos.ParquesContenedorApp

class ParquesAplicacion: Application() {
    lateinit var contenedor: ContenedorApp
    override fun onCreate() {
        super.onCreate()
        contenedor = ParquesContenedorApp(this)
    }
}