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
import com.example.proyectofinalandroid.modelo.Parque


@Composable
fun EditarParque(
    parque: Parque,
    onActualizarParque: (Parque) -> Unit
) {
    val id: Int = parque.id
    var nombreNuevo by remember { mutableStateOf(parque.nombre) }
    var extensionNueva by remember { mutableStateOf(parque.extension.toString()) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 80.dp, end = 80.dp), horizontalAlignment = Alignment.CenterHorizontally
    ) {
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
                value = extensionNueva,
                label = { Text(stringResource(R.string.extension)) },
                onValueChange = { extensionNueva = it },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                maxLines = 1
            )
        }


        val parqueActualizado = Parque(id, nombreNuevo, extensionNueva.toDouble())
        Spacer(modifier = Modifier.size(16.dp))
        Row {
            Button(onClick = { onActualizarParque(parqueActualizado) }) {
                Text(stringResource(R.string.guardar_cambios))
            }
        }
    }
}