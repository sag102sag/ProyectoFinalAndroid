package com.example.proyectofinalandroid.ui.pantallas

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.proyectofinalandroid.R
import com.example.proyectofinalandroid.modelo.ParqueVistoDB
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun EditarParqueVisitado(
    parque: ParqueVistoDB,
    onActualizarParque: (ParqueVistoDB) -> Unit
) {
    val id: Int = parque.id
    var fechaElegida: Long? by remember  { mutableStateOf(null)}
    var fechaFinal by remember { mutableStateOf(parque.fechaVisto) }
    var botonFechaPulsado by remember { mutableStateOf(false) }
    var comentarios by remember { mutableStateOf(parque.comentario) }
    var favorito by remember { mutableStateOf(parque.favorito) }
    var puntuacionFinal by remember { mutableStateOf(parque.puntuacion) }
    var puedeBajar by remember { mutableStateOf(false) }
    var puedeSubir by remember { mutableStateOf(false) }

    if (puntuacionFinal==0.0) puedeBajar=false else puedeBajar=true
    if (puntuacionFinal==5.0) puedeSubir=false else puedeSubir=true

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 50.dp, end = 50.dp), horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.size(16.dp))

        Row {
            TextField(
                value = parque.nombre,
                label = { Text(text = stringResource(R.string.nombre)) },
                onValueChange = {},
                enabled = false,
                modifier = Modifier.padding(16.dp)
            )
        }
        Row {
            TextField(
                value = parque.extension.toString(),
                label = { Text(text = stringResource(R.string.extension)) },
                onValueChange = {},
                enabled = false,
                modifier = Modifier.padding(16.dp)
            )
        }

        Row {
            TextField(
                value = comentarios,
                label = { Text(stringResource(R.string.comentarios)) },
                onValueChange = {comentarios = it},
                modifier = Modifier.padding(16.dp)
            )
        }

        Row {
            Column (modifier = Modifier.width(160.dp)){
                TextField(
                    value = puntuacionFinal.toString(),
                    label = { Text(stringResource(R.string.puntuaci_n)) },
                    onValueChange = {},
                    enabled = false,
                    modifier = Modifier.padding(16.dp)

                )
            }
            Column(modifier = Modifier.width(150.dp), verticalArrangement = Arrangement.Center){
                Row (verticalAlignment = Alignment.CenterVertically){
                    Button(onClick = {puntuacionFinal-=0.5}, enabled = puedeBajar, modifier = Modifier.padding(5.dp)) {
                        Text("-")
                    }
                    Button(onClick = {puntuacionFinal+=0.5}, enabled = puedeSubir, modifier = Modifier.padding(5.dp)) {
                        Text("+")
                    }
                }
            }
        }




        Text(stringResource(R.string.fecha_de_visita))
        Row {
            Button(onClick = { botonFechaPulsado = true }, modifier = Modifier.padding(16.dp)) {
                Text(text = stringResource(R.string.elegir_d_a))
            }
        }

        if(botonFechaPulsado){
            DatePickerMostrado2(
                onConfirm = { fecha ->
                    fechaElegida = fecha
                    botonFechaPulsado = false
                },
                onDismiss = { botonFechaPulsado = false })
        }

        if (fechaElegida != null) {
            val date = Date(fechaElegida!!)
            val formattedDate = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(date)
            fechaFinal = formattedDate.toString()
            Text(text = stringResource(R.string.fecha_seleccionada)+fechaFinal)
        }
        else {
            Text(text = stringResource(R.string.ninguna_fecha_seleccionada))
        }

        IconButton(
            onClick = { favorito = !favorito },
            modifier = Modifier
                .size(96.dp)
                .padding(4.dp)
        ) {
            Icon(
                painter = painterResource(
                    id = if (favorito) R.drawable.estrellallena else R.drawable.estrellavacia
                ),
                contentDescription = "Favorito",
            )
        }

        val nuevoParqueVistoDB = ParqueVistoDB(id=id, nombre = parque.nombre, extension = parque.extension, comentario = comentarios, fechaVisto = fechaFinal, puntuacion = puntuacionFinal, favorito = favorito)
        Text(nuevoParqueVistoDB.comentario)
        Text(nuevoParqueVistoDB.fechaVisto)
        Text(nuevoParqueVistoDB.puntuacion.toString())
        Text(nuevoParqueVistoDB.favorito.toString())
        Row {
            Button(onClick = { onActualizarParque(nuevoParqueVistoDB) }) {
                Text(stringResource(R.string.actualizar))
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePickerMostrado2(
    onConfirm: (Long?) -> Unit,
    onDismiss: () -> Unit
) {
    val datePickerState = rememberDatePickerState()

    DatePickerDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            TextButton(onClick = {
                onConfirm(datePickerState.selectedDateMillis)
                onDismiss()
            }) {
                Text(stringResource(R.string.aceptar))
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text(stringResource(R.string.cancelar))
            }
        }
    ) {
        DatePicker(state = datePickerState)
    }
}