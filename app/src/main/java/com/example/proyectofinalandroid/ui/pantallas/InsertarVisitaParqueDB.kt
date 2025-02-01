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
import com.example.proyectofinalandroid.modelo.ParqueVistoDB

@Composable
fun NuevaVisitaParqueDB(
    parque: Parque,
    onAnyadirParque: (ParqueVistoDB) -> Unit
)
{
    var id: Int = parque.id
    var nombre by remember { mutableStateOf(parque.nombre) }
    var extension by remember { mutableStateOf(parque.extension.toString()) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 80.dp, end = 80.dp), horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.size(16.dp))

        Row {
            TextField(
                value = nombre,
                label = { Text(text = stringResource(R.string.nombre)) },
                onValueChange = {},
                enabled = false,
                modifier = Modifier.padding(16.dp)
            )
        }

        Row {
            TextField(
                value = extension,
                label = { Text(text = stringResource(R.string.extension)) },
                onValueChange = {},
                enabled = false,
                modifier = Modifier.padding(16.dp)
            )
        }

        val nuevoParqueVistoDB: ParqueVistoDB = ParqueVistoDB(nombre = nombre, extension = extension.toDouble())

        Row {
            Button(onClick = { onAnyadirParque(nuevoParqueVistoDB) }) {
                Text(stringResource(R.string.a_adir_visita))
            }
        }
    }
}