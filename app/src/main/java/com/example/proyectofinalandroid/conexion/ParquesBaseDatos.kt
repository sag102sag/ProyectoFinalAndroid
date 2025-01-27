package com.example.proyectofinalandroid.conexion

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.proyectofinalandroid.dao.ParqueDao
import com.example.proyectofinalandroid.modelo.EspecieDB
import com.example.proyectofinalandroid.modelo.ParqueDB


@Database(entities = [EspecieDB::class, ParqueDB::class], version = 1, exportSchema = false)
abstract class ParquesBaseDatos: RoomDatabase() {

    abstract fun parqueDao(): ParqueDao

    companion object {
        @Volatile
        private var Instance: ParquesBaseDatos? = null

        fun obtenerBaseDatos(context: Context): ParquesBaseDatos {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, ParquesBaseDatos::class.java, "parquesdb")
                    .build()
                    .also { Instance = it }
            }
        }
    }
}