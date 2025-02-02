package com.example.proyectofinalandroid.ui.pantallas

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.proyectofinalandroid.R
import com.example.proyectofinalandroid.modelo.Especie


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ListaEspecies(
    listaEspecies: List<Especie>,
    onEspeciePulsada: (Especie) -> Unit,
    onAnyadirAvistamiento: (Especie) -> Unit,
    onAnyadirEspecie: () -> Unit,
    onEliminarEspecie: (Int) -> Unit
)
{
    var mostrarDialogo by remember { mutableStateOf(false) }
    var especieAEliminar by remember { mutableStateOf<Especie?>(null) }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = { onAnyadirEspecie() } // Acción al pulsar el FAB
            ) {
                Icon(
                    imageVector = Icons.Filled.Add,
                    contentDescription = stringResource(R.string.a_adir_especie)
                )
            }
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier.padding(innerPadding),

            ) {
            items(listaEspecies) { especie ->
                Card(
                    modifier = Modifier
                        .padding(6.dp)
                        .fillParentMaxWidth()
                        .combinedClickable(
                            onClick = { onEspeciePulsada(especie) },
                            onLongClick = {
                                especieAEliminar = especie
                                mostrarDialogo = true
                            }
                        )
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Column(modifier = Modifier.width(250.dp)) {
                            Text(
                                stringResource(R.string.nombreBien) + especie.nombre + "\n"+stringResource(R.string.descripci_n) + especie.descripcion +"\n"+ stringResource(
                                    R.string.tipoBien
                                ) +especie.tipo
                                ,
                                modifier = Modifier.padding(16.dp)
                            )
                        }
                        Column (
                            Modifier
                                .fillMaxWidth()
                                .padding(end = 20.dp), horizontalAlignment = Alignment.End){
                            Button(onClick = {onAnyadirAvistamiento(especie)}) {
                                Text(stringResource(R.string.a_adir_avistamiento), textAlign = TextAlign.Center)
                            }
                        }
                    }
                }
            }
        }
    }
    if (mostrarDialogo) {
        AlertDialog(
            onDismissRequest = {
                mostrarDialogo = false
            },
            title = { Text(text = stringResource(R.string.eliminar_avistamiento)) },
            text = {
                Text(
                    text = stringResource(R.string.est_seguro_de_eliminar_a) +
                            especieAEliminar!!.nombre + "?"
                )
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        mostrarDialogo = false
                    }
                ) {
                    Text(text = "No")
                }
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        onEliminarEspecie(especieAEliminar!!.id)
                        mostrarDialogo = false
                    }
                ) {
                    Text(text = "Sí")
                }
            }
        )
    }
}
