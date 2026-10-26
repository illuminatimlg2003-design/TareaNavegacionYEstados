package com.example.buzz.ui.pantallas

import android.widget.Toast
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.buzz.R
import com.example.buzz.data.guardarPregunta
import com.example.buzz.model.Pregunta
import com.example.buzz.ui.componentes.BotonRespuesta

@Composable
fun EscribirPregunta(
    sumarTap: () -> Unit
) {
    var textoPregunta by remember { mutableStateOf("") }
    var valorSeleccionado by remember { mutableStateOf<Boolean?>(null) }

    val contexto = LocalContext.current

    fun crearPregunta(pregunta: String, respuesta: Boolean) {

            val preguntaNueva = Pregunta(
                pregunta = pregunta,
                respuesta = respuesta
            )

            guardarPregunta(contexto, preguntaNueva)
        //este texto va hardcodeado porque de otra manera peta el toast
            Toast.makeText(contexto,"Pregunta guardada correctamente", Toast.LENGTH_LONG).show()
             textoPregunta = ""
            valorSeleccionado = null

    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            value = textoPregunta,
            onValueChange = { textoPregunta = it },
            label = { Text(stringResource(R.string.escribe_una_pregunta)) },
            modifier = Modifier.fillMaxWidth()
        )


        Row(modifier = Modifier.fillMaxWidth()) {
            BotonRespuesta(
                tipo = false,
                modifier = Modifier
                    .weight(1f)
                    .padding(4.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .border(
                        width = 3.dp,
                        color = if (valorSeleccionado == false) Color.Yellow
                        else Color.Transparent,
                        shape = RoundedCornerShape(12.dp)
                    ),
                onClick = { valorSeleccionado = false
                    sumarTap()}
            )

            BotonRespuesta(
                tipo = true,
                modifier = Modifier
                    .weight(1f)
                    .padding(4.dp)
                    .then(
                        if (valorSeleccionado == true)
                            Modifier.border(3.dp, Color.Yellow, RoundedCornerShape(12.dp))
                        else Modifier
                    ),
                onClick = {   valorSeleccionado = true
                sumarTap()}
            )
        }

        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp),
            enabled = textoPregunta.isNotBlank() &&
                    valorSeleccionado != null,
            onClick = {
                sumarTap()
                crearPregunta(
                    textoPregunta,
                    valorSeleccionado!!
                )
            }
        ) {
            Text(stringResource(R.string.a_adir_pregunta))
        }
    }
}




@Preview(showBackground = true)
@Composable
fun PreviewEscribir() {
     EscribirPregunta(
         sumarTap = TODO()
     )
}