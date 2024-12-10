package com.example.lazytareas.ui.theme

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Arrangement.Absolute.spacedBy
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Blue
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lazytareas.Tareas

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TareasApp() {
    // Lista inicial de tareas
    var tareas by remember {
        mutableStateOf(
            listOf(
                Tareas(1, "Estudiar Compose", false),
                Tareas(2, "Comprar verduras", true),
                Tareas(3, "Revisar correos", false),
                Tareas(4, "Estudiar Acceso a datos", false),
                Tareas(5, "Comprar pan", true),
                Tareas(6, "Revisar wasap", false),
                Tareas(7, "Estudiar fol", false),
                Tareas(8, "Comprar agua", true),
                Tareas(9, "Revisar buzon", false)
            )
        )
    }

    // Estructura general de la pantalla
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Lista de Tareas", fontSize = 20.sp) }
            )
        }
    ) { padding ->

        LazyColumnAlbumes(
            modifier = Modifier.padding(padding),
            tareas = tareas,
            onItemSelected = { tareaSeleccionada ->
                // Actualiza el estado de la tarea seleccionada
                tareas = tareas.map {
                    if (it.id == tareaSeleccionada.id) it.copy(completada = !it.completada) else it
                }
            }
        )
    }
}

// Función LazyColumn para mostrar la lista de tareas
@Composable
fun LazyColumnAlbumes(modifier: Modifier, tareas: List<Tareas>, onItemSelected: (Tareas) -> Unit) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp) // Espaciado entre elementos
    ) {
        // Renderiza cada elemento de la lista usando items
        items(tareas) { tarea ->
            ItemTarea(tarea, onItemSelected)
        }
    }
}


@Composable
fun ItemTarea(tareas: Tareas, onItemSelected: (Tareas) -> Unit) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = Color.DarkGray
        ),
        border = BorderStroke(2.dp, Color.Black),
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onItemSelected(tareas) }
            .padding(8.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {

            Text(text = tareas.nom, fontSize = 18.sp, color = Color.White)
            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = if (tareas.completada) "Estado: Completada" else "Estado: No completada",
                color = if (tareas.completada) Color.Green else Color.Red
            )
            Icon(
                imageVector = if(tareas.completada) Icons.Default.Done else Icons.Default.Close,
                contentDescription = "Icono de información",
                tint = Color.White, // Color del ícono
                modifier = Modifier.fillMaxSize() // Tamaño del ícono
            )
        }
    }
}

