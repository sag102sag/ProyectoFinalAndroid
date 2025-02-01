package com.example.proyectofinalandroid.ui.pantallas

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.proyectofinalandroid.R
import com.example.proyectofinalandroid.modelo.ParqueVistoDB

@Composable
fun ParquesVisitados(
   // listaParquesVisitados: List<ParqueVistoDB>
)
{
    Text("HOLA")
    /*LazyColumn(
        modifier = Modifier.padding(16.dp),

        ) {
        items(listaParquesVisitados) { parque ->
            Card(
                modifier = Modifier
                    .padding(6.dp)
                    .fillParentMaxWidth()
                    .clickable {

                    }
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Column(modifier = Modifier.width(250.dp)) {
                        Text(
                            stringResource(R.string.nombreBien) + parque.nombre + stringResource(R.string.extensi_n) + parque.extension,
                            modifier = Modifier.padding(16.dp)
                        )
                    }
                    Column (
                        Modifier
                            .fillMaxWidth()
                            .padding(end = 20.dp), horizontalAlignment = Alignment.End){
                        Button(onClick = {}) {
                            Text(stringResource(R.string.a_adir_visita), textAlign = TextAlign.Center)
                        }
                    }
                }
            }
        }
    }*/
}