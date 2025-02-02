package com.example.proyectofinalandroid.ui.pantallas

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.unit.dp
import com.example.proyectofinalandroid.R
import com.example.proyectofinalandroid.modelo.Especie

@Composable
fun InsertarEspecie(
    onInsertarEspecie: (Especie) -> Unit
) {
    var nombreNuevo by remember { mutableStateOf("") }
    var descripcionNueva by remember { mutableStateOf("") }
    var tipoNuevo by remember { mutableStateOf("") }

    Column (modifier = Modifier
        .fillMaxWidth()
        .padding(start = 80.dp, end = 80.dp),horizontalAlignment = Alignment.CenterHorizontally){
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
                value = descripcionNueva,
                label = { Text(stringResource(R.string.descripci_n)) },
                onValueChange = { descripcionNueva = it },
                maxLines = 1
            )
        }
        Row {
            TextField(
                value = tipoNuevo,
                label = { Text(stringResource(R.string.tipo)) },
                onValueChange = { tipoNuevo = it },
                maxLines = 1
            )
        }


        val nuevaEspecie = Especie(9999, nombreNuevo, descripcionNueva, tipoNuevo)
        Spacer(modifier = Modifier.size(16.dp))
        Row {
            Button(onClick = {onInsertarEspecie(nuevaEspecie)}) {
                Text(stringResource(R.string.insertar))
            }
        }
    }
}