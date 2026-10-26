
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
import com.example.buzz.data.cargarPreguntas
import com.example.buzz.ui.componentes.BotonMovimiento
import com.example.buzz.ui.componentes.BotonRespuesta
import com.example.buzz.ui.componentes.TextoRespuesta


@Composable
fun Speedrun(  sumarIncorrecta:()->Unit,
           sumarCorrecta: () -> Unit,
           sumarTap: () -> Unit) {

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
    fun responder(respuesta: Boolean) {
        sumarTap()

        if (respondidas[indiceActual] == null) {
            respondidas[indiceActual] = respuesta
            respuestaElegida = respuesta

            if (respuesta == pregunta.respuesta) {
                sumarCorrecta()
            } else {
                sumarIncorrecta()
            }
        }
    }
    val preguntasContestadas = respondidas.count { it != null }

    val progreso = if (preguntas.isNotEmpty()) {
        preguntasContestadas.toFloat() / preguntas.size
    } else {
        0f
    }
    Column(modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally) {
        LinearProgressIndicator(
            progress = { progreso },
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        )
        Column(Modifier.weight(1f)) {
            PreguntaVista(
                pregunta.pregunta,
                pregunta.imagen, modifierImagen = Modifier.size(0.dp)

            )}
        TextoRespuesta(respuestaElegida, pregunta.respuesta)
        Column(Modifier.weight(1f), verticalArrangement = Arrangement.Bottom) {
            Row() {
                BotonRespuesta(false, Modifier.weight(1f) ,onClick = {responder(false)})
                BotonRespuesta(true, Modifier.weight(1f),onClick = {responder(true)})
            }
            Row(modifier = Modifier.fillMaxWidth()) {
                BotonMovimiento(false, Modifier.weight(1f)  ,
                    onClick = {
                        sumarTap()
                        if (indiceActual > 0) {
                            indiceActual--
                            respuestaElegida = respondidas[indiceActual]
                        }else indiceActual=preguntas.lastIndex;
                        respuestaElegida=respondidas[indiceActual]
                    })
                BotonMovimiento(
                    true,
                    modifier = Modifier.weight(1f),
                    onClick = {
                        sumarTap()
                        if (indiceActual < preguntas.lastIndex) {
                            indiceActual++
                        } else {
                            indiceActual = 0
                        }

                        respuestaElegida = respondidas[indiceActual]
                    }
                )

            }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun PreviewSpeed() {
    Speedrun(
        sumarIncorrecta = TODO(),
        sumarCorrecta = TODO(),
        sumarTap = TODO()
    )
}