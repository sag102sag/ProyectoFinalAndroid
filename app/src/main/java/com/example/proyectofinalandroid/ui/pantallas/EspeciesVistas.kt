package com.example.proyectofinalandroid.ui.pantallas

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
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
import com.example.proyectofinalandroid.ui.ParquesUIState

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun EspeciesVistas(
    uiState: ParquesUIState,
    onEditarEspecieVista: (EspecieVistaDB) -> Unit,
    onEliminarEspecieVista: (EspecieVistaDB) -> Unit,
    onObtenerEspeciesVistas:() -> Unit
)
{
    when(uiState){
        is ParquesUIState.ObtenerExitoEspeciesVistas -> {
            EspeciesVistas2(
                listaEspeciesVistas = uiState.especiesVistas,
                onEditarEspecieVista = onEditarEspecieVista,
                onEliminarEspecieVista = onEliminarEspecieVista
            )
        }
        is ParquesUIState.Cargando -> Unit
        else -> onObtenerEspeciesVistas()
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun EspeciesVistas2(
    listaEspeciesVistas: List<EspecieVistaDB>,
    onEditarEspecieVista: (EspecieVistaDB) -> Unit,
    onEliminarEspecieVista: (EspecieVistaDB) -> Unit
)
{
    var mostrarDialogo by remember { mutableStateOf(false) }
    var especieVistaAEliminar by remember { mutableStateOf<EspecieVistaDB?>(null) }
    LazyColumn(
        modifier = Modifier.padding(16.dp),

        ) {
        items(listaEspeciesVistas) { especie ->
            Card(
                modifier = Modifier
                    .padding(6.dp)
                    .fillParentMaxWidth()
                    .combinedClickable(
                        onClick = { onEditarEspecieVista(especie) },
                        onLongClick = {
                            especieVistaAEliminar = especie
                            mostrarDialogo = true
                        }
                    )

            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Column(modifier = Modifier.width(250.dp)) {
                        Text(
                            stringResource(R.string.nombreBien) + especie.nombre + "\n"+ stringResource(
                                R.string.cantidad_vistaBien
                            ) + especie.cantidadVista,
                            modifier = Modifier.padding(16.dp)
                        )
                    }
                    Column (modifier = Modifier
                        .width(250.dp)
                        .padding(16.dp), horizontalAlignment = Alignment.End, verticalArrangement = Arrangement.Center){
                        if(especie.favorito) {
                            Icon(
                                painter = painterResource(R.drawable.estrellallena),
                                contentDescription = stringResource(R.string.favorito)
                            )
                        }
                        else{
                            Icon(
                                painter = painterResource(R.drawable.estrellavacia),
                                contentDescription = stringResource(R.string.no_favorito)
                            )
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
                                    especieVistaAEliminar!!.nombre + "?"
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
                                onEliminarEspecieVista(especieVistaAEliminar!!)
                                mostrarDialogo = false
                            }
                        ) {
                            Text(text = "Sí")
                        }
                    }
                )
            }
        }
    }
}