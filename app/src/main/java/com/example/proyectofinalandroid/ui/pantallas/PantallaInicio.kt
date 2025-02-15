package com.example.proyectofinalandroid.ui.pantallas

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.proyectofinalandroid.R
import com.example.proyectofinalandroid.ui.ParquesUIState

@Composable
fun PantallaInicio(
    appUIState: ParquesUIState,
)
{
    when (appUIState) {
        is ParquesUIState.Cargando -> PantallaCargando()
        is ParquesUIState.Error -> PantallaError()
        is ParquesUIState.ObtenerExitoParques -> Inicio(

        )
        else -> Unit
    }
}

@Composable
fun PantallaCargando(modifier: Modifier = Modifier) {
    Image(
        modifier = modifier.size(200.dp),
        painter = painterResource(R.drawable.cargando),
        contentDescription = "Cargando"
    )
}

@Composable
fun PantallaError(modifier: Modifier = Modifier) {
    Column(modifier= Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        Text("Error :(")
        Image(
            modifier = modifier.size(200.dp),
            painter = painterResource(R.drawable.error),
            contentDescription = "Error"
        )
    }
}

@Composable
fun Inicio()
{
    Box(modifier = Modifier.fillMaxSize())
    {
        Image(
            painter = painterResource(R.drawable.tree),
            contentDescription = "Arbol",
            modifier = Modifier.fillMaxSize().graphicsLayer { alpha=0.15f },
            contentScale= ContentScale.Crop,
        )
       /* Row (modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center){
        Text("¡Bienvenido!", fontSize = 60.sp, modifier = Modifier.padding(top = 36.dp))
        }*/
    }

}