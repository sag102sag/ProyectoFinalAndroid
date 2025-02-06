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
import com.example.proyectofinalandroid.modelo.Parque
import com.example.proyectofinalandroid.ui.ParquesUIState

@Composable
fun ListaParques(
    uiState: ParquesUIState,
    onParquePulsado: (Parque) -> Unit,
    onInsertarNuevoParque:() -> Unit,
    onAnyadirNuevaVisita:(Parque) -> Unit,
    onEliminarParque:(Int) -> Unit,
    onObtenerParques:() ->Unit
){
    when(uiState)
    {
        is ParquesUIState.ObtenerExitoParques -> {
            ListaParques2(
                listaParques=uiState.parques,
            onParquePulsado= onParquePulsado,
            onInsertarNuevoParque=onInsertarNuevoParque,
            onAnyadirNuevaVisita=onAnyadirNuevaVisita,
            onEliminarParque=onEliminarParque
            )
        }
        is ParquesUIState.Cargando -> Unit
        else -> onObtenerParques()
    }
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ListaParques2(
    listaParques: List<Parque>,
    onParquePulsado: (Parque) -> Unit,
    onInsertarNuevoParque:() -> Unit,
    onAnyadirNuevaVisita:(Parque) -> Unit,
    onEliminarParque:(Int) -> Unit

)
{
    var mostrarDialogo by remember { mutableStateOf(false) }
    var parqueAEliminar by remember { mutableStateOf<Parque?>(null) }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick =  {onInsertarNuevoParque()} // Acción al pulsar el FAB
            ) {
                Icon(
                    imageVector = Icons.Filled.Add,
                    contentDescription = stringResource(R.string.insertar_parque)
                )
            }
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier.padding(innerPadding),

            ) {
            items(listaParques) { parque ->
                Card(
                    modifier = Modifier
                        .padding(6.dp)
                        .fillParentMaxWidth()
                        .combinedClickable (
                            onClick = {onParquePulsado(parque)},
                            onLongClick = {parqueAEliminar=parque
                            mostrarDialogo=true}
                        )
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                    Column(modifier = Modifier.width(250.dp)) {
                    Text(
                        stringResource(R.string.nombreBien) + parque.nombre + "\n"+ stringResource(R.string.extension) + parque.extension,
                        modifier = Modifier.padding(16.dp)
                    )
                    }
                    Column (
                        Modifier
                            .fillMaxWidth()
                            .padding(end = 20.dp), horizontalAlignment = Alignment.End){
                        Button(onClick = {onAnyadirNuevaVisita(parque)}) {
                            Text(stringResource(R.string.a_adir_visita), textAlign = TextAlign.Center)
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
                            parqueAEliminar!!.nombre + "?"
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
                        onEliminarParque(parqueAEliminar!!.id)
                        mostrarDialogo = false
                    }
                ) {
                    Text(text = "Sí")
                }
            }
        )
    }

}