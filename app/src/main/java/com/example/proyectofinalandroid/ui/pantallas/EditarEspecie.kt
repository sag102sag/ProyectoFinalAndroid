package com.example.proyectofinalandroid.ui.pantallas

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.proyectofinalandroid.R
import com.example.proyectofinalandroid.modelo.Especie
import com.example.proyectofinalandroid.modelo.Parque

@Composable
fun EditarEspecie(
    especie: Especie,
    onActualizarEspecie: (Especie) -> Unit
) {
    var id: Int = especie.id
    var nombreNuevo by remember { mutableStateOf(especie.nombre) }
    var descripcionNueva by remember { mutableStateOf(especie.descripcion) }
    var tipoNuevo by remember { mutableStateOf(especie.tipo) }

    Column (modifier = Modifier
        .fillMaxWidth()
        .padding(start = 80.dp, end = 80.dp),horizontalAlignment = Alignment.CenterHorizontally){
        Spacer(modifier = Modifier.size(16.dp))
        Row {
            TextField(
                value = id.toString(),
                label = { Text(text = stringResource(R.string.id)) },
                onValueChange = {},
                enabled = false
            )
        }
        Spacer(modifier = Modifier.size(16.dp))
        Row {
            TextField(
                value = nombreNuevo,
                label = { Text(stringResource(R.string.nombre)) },
                onValueChange = { nombreNuevo = it }
            )
        }
        Spacer(modifier = Modifier.size(16.dp))
        Row {
            TextField(
                value = descripcionNueva.toString(),
                label = { Text(stringResource(R.string.descripcion)) },
                onValueChange = { descripcionNueva = it },
            )
        }
        Spacer(modifier = Modifier.size(16.dp))
        Row {
            TextField(
                value = tipoNuevo,
                label = { Text(stringResource(R.string.tipo)) },
                onValueChange = { tipoNuevo = it },
            )
        }


        var especieActualizada: Especie = Especie(id, nombreNuevo, descripcionNueva, tipoNuevo)
        Spacer(modifier = Modifier.size(16.dp))
        Row {
            Button(onClick = {onActualizarEspecie(especieActualizada)}) {
                Text(stringResource(R.string.guardar_cambios))
            }
        }
    }


}