package com.example.proyectofinalandroid.ui

import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Place
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.proyectofinalandroid.R
import com.example.proyectofinalandroid.modelo.DrawerMenu
import com.example.proyectofinalandroid.modelo.Ruta
import com.example.proyectofinalandroid.ui.pantallas.AnyadirEspecieVista
import com.example.proyectofinalandroid.ui.pantallas.EditarEspecie
import com.example.proyectofinalandroid.ui.pantallas.EditarEspecieVista
import com.example.proyectofinalandroid.ui.pantallas.EditarParque
import com.example.proyectofinalandroid.ui.pantallas.EditarParqueVisitado
import com.example.proyectofinalandroid.ui.pantallas.EspeciesVistas
import com.example.proyectofinalandroid.ui.pantallas.InsertarEspecie
import com.example.proyectofinalandroid.ui.pantallas.InsertarParque
import com.example.proyectofinalandroid.ui.pantallas.ListaEspecies
import com.example.proyectofinalandroid.ui.pantallas.ListaParques
import com.example.proyectofinalandroid.ui.pantallas.NuevaVisitaParqueDB
import com.example.proyectofinalandroid.ui.pantallas.PantallaInicio
import com.example.proyectofinalandroid.ui.pantallas.ParquesVisitados
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

enum class Pantallas(@StringRes val titulo: Int){
    Inicio(titulo = R.string.inicio),

    // PANTALLAS PARQUES
    ListarParques(titulo= R.string.parques),
    EditarParque(titulo= R.string.editar_parque),
    InsertarParque(titulo= R.string.nuevo_parque),
    InsertarParqueVisitado(titulo= R.string.nueva_visita),
    ListarParquesVisitados(titulo= R.string.parques_visitados),
    EditarParqueVisitado(titulo= R.string.editar_visita),

    // PANTALLAS ESPECIES
    ListarEspecies(titulo = R.string.especies),
    EditarEspecie(titulo = R.string.editarespecie),
    InsertarEspecie(titulo = R.string.nueva_especie),
    EspeciesVistas(titulo = R.string.especies_vistas),
    InsertarEspecieVista(titulo= R.string.insertar_especie_vista),
    EditarEspecieVista(titulo= R.string.editar_avistamiento)
}

val listaRutas = listOf(
    Ruta(Pantallas.ListarParques.titulo, Pantallas.ListarParques.name, Icons.Filled.Place, Icons.Outlined.Place),
    Ruta(Pantallas.Inicio.titulo, Pantallas.Inicio.name, Icons.Filled.Home, Icons.Outlined.Home),
    Ruta(Pantallas.ListarEspecies.titulo, Pantallas.ListarEspecies.name, Icons.Filled.Info, Icons.Outlined.Info)
)

val menu = arrayOf(
    DrawerMenu(Icons.Filled.CheckCircle, Pantallas.ListarParquesVisitados.titulo, Pantallas.ListarParquesVisitados.name),
    DrawerMenu(Icons.Filled.Check, Pantallas.EspeciesVistas.titulo, Pantallas.EspeciesVistas.name)
)

@Composable
fun ParquesApp(
    viewModel: ParquesViewModel = viewModel(factory = ParquesViewModel.Factory),
    navController: NavHostController = rememberNavController(),
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    drawerState: DrawerState = rememberDrawerState(initialValue = DrawerValue.Closed),
){
    val pilaRetroceso by navController.currentBackStackEntryAsState()

    val uiState: ParquesUIState

    val pantallaActual = Pantallas.valueOf(
        pilaRetroceso?.destination?.route ?: Pantallas.Inicio.name
    )
    var selectedItem by remember { mutableIntStateOf(1) }

    ModalNavigationDrawer(  // --> Esto envuelve todo el scaffold
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                DrawerContent(
                    menu = menu,
                    pantallaActual = pantallaActual
                ) { ruta ->
                    coroutineScope.launch {
                        drawerState.close()
                    }


                    navController.navigate(ruta)
                }
            }
        },
    ) {

        // --> Desde aquí
        Scaffold(
            /* topBar = {
            AppTopBar(
                pantallaActual = pantallaActual,
                puedeNavegarAtras = navController.previousBackStackEntry != null,
                onNavegarAtras = {navController.navigateUp()}
            )
        },*/
            topBar = {
                AppTopBar(
                    pantallaActual = pantallaActual,
                    drawerState = drawerState
                )
            },

            bottomBar = {
                NavigationBar {
                    listaRutas.forEachIndexed { indice, ruta ->
                        NavigationBarItem(
                            icon = {
                                if (selectedItem == indice)
                                    Icon(
                                        imageVector = ruta.iconoLleno,
                                        contentDescription = stringResource(id = ruta.nombre)
                                    )
                                else
                                    Icon(
                                        imageVector = ruta.iconoVacio,
                                        contentDescription = stringResource(id = ruta.nombre)
                                    )
                            },
                            label = { Text(stringResource(id = ruta.nombre)) },
                            selected = selectedItem == indice,
                            onClick = {
                                selectedItem = indice
                                navController.navigate(ruta.ruta)
                            }
                        )
                    }
                }
            },
        ) { innerPadding ->
            var uiState = viewModel.parquesUIState

            NavHost(
                navController = navController,
                startDestination = Pantallas.Inicio.name,
                modifier = Modifier.padding(innerPadding)
            ) {
                // Grafo de las rutas
                composable(route = Pantallas.Inicio.name) {
                    PantallaInicio(
                        appUIState = uiState
                    )
                }
                // ---------------------------- PARQUES ---------------------------------
                composable(route = Pantallas.ListarParques.name) {
                        ListaParques(
                            uiState=uiState,
                            onParquePulsado = {
                                viewModel.actualizarParquePulsado(it)
                                navController.navigate(Pantallas.EditarParque.name)
                            },
                            onInsertarNuevoParque = { navController.navigate(Pantallas.InsertarParque.name) },
                            onAnyadirNuevaVisita = {
                                viewModel.actualizarParquePulsado(it)
                                navController.navigate(Pantallas.InsertarParqueVisitado.name)
                            },
                            onEliminarParque = { viewModel.eliminarParque(it) },
                            onObtenerParques = {viewModel.obtenerParques()}
                        )
                }
                composable(route = Pantallas.EditarParque.name) {
                    EditarParque(
                        parque = viewModel.parquePulsado,
                        onActualizarParque = {
                            viewModel.actualizarParque(it.id, it)
                            viewModel.obtenerParques()
                            navController.navigate(Pantallas.ListarParques.name)
                        }
                    )
                }
                composable(route = Pantallas.InsertarParque.name) {
                    InsertarParque(
                        onInsertarParque = {
                            viewModel.insertarParque(it)
                            viewModel.obtenerParques()
                            navController.navigate(Pantallas.ListarParques.name)
                        }
                    )
                }
                composable(route = Pantallas.InsertarParqueVisitado.name) {
                    NuevaVisitaParqueDB(
                        parque = viewModel.parquePulsado,
                        onAnyadirParque = {
                            viewModel.insertarParqueBD(it)
                            navController.navigate(Pantallas.ListarParques.name)
                        }
                    )
                }
                composable(route = Pantallas.ListarParquesVisitados.name) {
                        ParquesVisitados(
                            uiState=uiState,
                            onEditarParque = {
                                viewModel.actualizarParqueDBPulsado(it)
                                navController.navigate(Pantallas.EditarParqueVisitado.name)
                            },
                            onEliminarParqueVisitado = { viewModel.eliminarParqueBD(it) },
                            onObtenerParquesVistos = {viewModel.obtenerParquesVistosDB()}
                        )
                }
                composable(route = Pantallas.EditarParqueVisitado.name) {
                    EditarParqueVisitado(
                        parque = viewModel.parqueVistoDBPulsado,
                        onActualizarParque = {
                            viewModel.actualizarParqueBD(it)
                            navController.navigate(Pantallas.ListarParquesVisitados.name)
                        }
                    )

                }

                // ---------------------------- ESPECIES ---------------------------------

                composable(route = Pantallas.ListarEspecies.name) {
                        ListaEspecies(
                            uiState = uiState,
                            onEspeciePulsada = {
                                viewModel.actualizarEspeciePulsada(it)
                                navController.navigate((Pantallas.EditarEspecie.name))
                            },
                            onAnyadirAvistamiento = {
                                viewModel.actualizarEspeciePulsada(it)
                                navController.navigate(Pantallas.InsertarEspecieVista.name)
                            },
                            onAnyadirEspecie = { navController.navigate(Pantallas.InsertarEspecie.name) },
                            onEliminarEspecie = { viewModel.eliminarEspecie(it) },
                            onCargarEspeces = {viewModel.obtenerEspecies()}
                        )

                }
                composable(route = Pantallas.InsertarEspecie.name) {
                    InsertarEspecie(
                        onInsertarEspecie = {
                            viewModel.insertarEspecie(it)
                            viewModel.obtenerEspecies()
                            navController.navigate(Pantallas.ListarEspecies.name)
                        }
                    )
                }
                composable(route = Pantallas.EditarEspecie.name) {
                    EditarEspecie(
                        especie = viewModel.especiePulsada,
                        onActualizarEspecie = {
                            viewModel.actualizarEspecie(it.id, it)
                            viewModel.obtenerEspecies()
                            navController.navigate(Pantallas.ListarEspecies.name)
                        }
                    )
                }
                composable(route = Pantallas.InsertarEspecieVista.name) {
                    AnyadirEspecieVista(
                        especie = viewModel.especiePulsada,
                        onAnyadirEspecie = {
                            viewModel.insertarEspecieBD(it)
                            navController.navigate(Pantallas.ListarEspecies.name)
                        }
                    )
                }
                composable(route = Pantallas.EspeciesVistas.name) {
                        EspeciesVistas(
                            uiState=uiState,
                            onEditarEspecieVista = {
                                viewModel.actualizarEspecieDBPulsada(it)
                                navController.navigate(Pantallas.EditarEspecieVista.name)
                            },
                            onEliminarEspecieVista = { viewModel.eliminarEspecieVista(it) },
                            onObtenerEspeciesVistas = {viewModel.obtenerEspeciesVistasDB()}
                        )

                }
                composable(route = Pantallas.EditarEspecieVista.name) {
                    EditarEspecieVista(
                        especie = viewModel.especieVistaDBPulsada,
                        onActualizarAvistamiento = {
                            viewModel.actualizarEspecieBD(it)
                            navController.navigate(Pantallas.EspeciesVistas.name)
                        }
                    )
                }
            }
        }
    }// --> Hasta aquí
}
/*
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTopBar(
    pantallaActual: Pantallas,
    puedeNavegarAtras: Boolean,
    onNavegarAtras: () -> Unit,
    modifier: Modifier = Modifier
){
    TopAppBar(
        title = { Text(text = stringResource(id = pantallaActual.titulo)) },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        navigationIcon = {
            if(puedeNavegarAtras) {
                IconButton(onClick = onNavegarAtras) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = stringResource(id = R.string.atras)
                    )
                }
            }
        },
        modifier = modifier
    )
}*/

@Composable
private fun DrawerContent(
    menu: Array<DrawerMenu>,
    pantallaActual: Pantallas,
    onMenuClick: (String) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp),
            contentAlignment = Alignment.Center
        ) {
            Image(
                modifier = Modifier.size(150.dp),
                imageVector = Icons.Filled.AccountCircle,
                contentScale = ContentScale.Crop,
                contentDescription = null
            )
        }
        Spacer(modifier = Modifier.height(12.dp))
        menu.forEach {
            NavigationDrawerItem(
                label = { Text(text = stringResource(id = it.titulo)) },
                icon = { Icon(imageVector = it.icono, contentDescription = null) },
                selected = it.titulo == pantallaActual.titulo,
                onClick = {
                    onMenuClick(it.ruta)
                }
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTopBar(
    pantallaActual: Pantallas,
    drawerState: DrawerState?,
    modifier: Modifier = Modifier
){
    val coroutineScope = rememberCoroutineScope()

    TopAppBar(
        title = { Text(text = stringResource(id = pantallaActual.titulo)) },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),

       navigationIcon = {
            if (drawerState != null) {
                if(true){
                    IconButton(
                        onClick = {
                            coroutineScope.launch {
                                drawerState.open()
                            }
                        }) {
                        Icon(
                            imageVector = Icons.Filled.Menu,
                            contentDescription = stringResource(id = R.string.atras)
                        )
                    }
                }
            }
        },
        modifier = modifier
    )
}