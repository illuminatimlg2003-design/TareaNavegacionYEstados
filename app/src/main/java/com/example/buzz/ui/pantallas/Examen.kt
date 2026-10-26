package com.example.buzz.ui.pantallas



import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import com.example.buzz.ui.componentes.PreguntaVista
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import com.example.buzz.data.cargarPreguntas
import com.example.buzz.ui.componentes.BotonMovimiento
import com.example.buzz.ui.componentes.BotonRespuesta
import com.example.buzz.ui.componentes.BotonRandom
import com.example.buzz.ui.componentes.DialogResultado
import com.example.buzz.ui.componentes.TextoRespuesta


@Composable
fun Examen(pila: NavBackStack<NavKey>) {

    val contexto = LocalContext.current
    val preguntas = cargarPreguntas(contexto)


    var indiceActual by remember { mutableIntStateOf(0) }

    val pregunta = preguntas[indiceActual]

    var respuestaElegida by remember {
        mutableStateOf<Boolean?>(null)  }


    val respondidas = remember(preguntas) {
        mutableStateListOf<Boolean?>().apply {
            repeat(preguntas.size) {
                add(null)
            }
        }
    }
    var mostrarResultado by remember { mutableStateOf(false) }

    val aciertos = respondidas.indices.count { i ->
        respondidas[i] != null &&
                respondidas[i] == preguntas[i].respuesta
    }


    fun responder(respuesta: Boolean) {

        if (respondidas[indiceActual] == null) {
            respondidas[indiceActual] = respuesta
            respuestaElegida = respuesta
        }
    }




    Column(modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally) {
        if (mostrarResultado) {
            DialogResultado(
                aciertos = aciertos,
                totalPreguntas = preguntas.size,
                onCerrar = {
                    pila.removeLastOrNull()
                }
            )
        }
        Column(Modifier.weight(1f)) {
            PreguntaVista(
                pregunta.pregunta,
                pregunta.imagen

            )}
        TextoRespuesta(respuestaElegida, pregunta.respuesta)
        Column(Modifier.weight(1f), verticalArrangement = Arrangement.Bottom) {
            Row() {
                BotonRespuesta(false, Modifier.weight(1f) ,onClick = {responder(false)})
                BotonRespuesta(true, Modifier.weight(1f),onClick = {responder(true)})
            }
            Row(modifier = Modifier.fillMaxWidth()) {

                BotonMovimiento(
                    true,
                    modifier = Modifier.weight(1f),
                    onClick = {
                        if (respondidas[indiceActual] != null) {
                            if (indiceActual < preguntas.lastIndex) {
                                indiceActual++
                                respuestaElegida = respondidas[indiceActual]
                            } else {
                                mostrarResultado = true
                            }
                        }
                    }
                )

            }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun PreviewExamen() {
    Speedrun(
        sumarIncorrecta = TODO(),
        sumarCorrecta = TODO(),
        sumarTap = TODO()
    )
}