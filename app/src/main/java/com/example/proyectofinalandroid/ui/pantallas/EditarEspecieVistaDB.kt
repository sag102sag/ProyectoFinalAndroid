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
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
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
import com.example.proyectofinalandroid.modelo.EspecieVistaDB
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun EditarEspecieVista(
    especie: EspecieVistaDB,
    onActualizarAvistamiento: (EspecieVistaDB) -> Unit
)
{
    val nombre by remember { mutableStateOf(especie.nombre) }
    val descripcion by remember { mutableStateOf(especie.descripcion) }
    val tipo by remember { mutableStateOf(especie.tipo) }
    var fechaElegida: Long? by remember  { mutableStateOf(null)}
    var fechaFinal by remember { mutableStateOf(especie.fechaVisto) }
    var botonFechaPulsado by remember { mutableStateOf(false) }
    var comentarios by remember { mutableStateOf(especie.comentario) }
    var favorito by remember { mutableStateOf(especie.favorito) }
    var cantidadVista by remember { mutableStateOf(especie.cantidadVista) }
    var puedeBajar by remember { mutableStateOf(false) }

    if (cantidadVista==1) puedeBajar=false else puedeBajar=true


    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 50.dp, end = 50.dp), horizontalAlignment = Alignment.CenterHorizontally
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
                value = descripcion,
                label = { Text(text = stringResource(R.string.descripci_n)) },
                onValueChange = {},
                enabled = false,
                modifier = Modifier.padding(16.dp)
            )
        }

        Row {
            TextField(
                value = tipo,
                label = { Text(text = stringResource(R.string.tipo)) },
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
                    value = cantidadVista.toString(),
                    label = { Text(stringResource(R.string.cantidad_vista)) },
                    onValueChange = {},
                    enabled = false,
                    modifier = Modifier.padding(16.dp)

                )
            }
            Column(modifier = Modifier.width(150.dp), verticalArrangement = Arrangement.Center){
                Row (verticalAlignment = Alignment.CenterVertically){
                    Button(onClick = {cantidadVista-=1}, enabled = puedeBajar, modifier = Modifier.padding(5.dp)) {
                        Text("-")
                    }
                    Button(onClick = {cantidadVista+=1}, modifier = Modifier.padding(5.dp)) {
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
            DatePickerMostrado(
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

        val especieNuevaDB = EspecieVistaDB(id = especie.id, nombre = nombre, descripcion = descripcion, tipo = tipo, comentario = comentarios, fechaVisto = fechaFinal, cantidadVista = cantidadVista, favorito = favorito)

        Row {
            Button(onClick = { onActualizarAvistamiento(especieNuevaDB) }) {
                Text(stringResource(R.string.actualizar_avistamiento))
            }
        }
    }
}

