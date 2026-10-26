package com.example.buzz.ui.componentes

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.buzz.R


@Composable
fun TextoRespuesta(
    respuestaElegida: Boolean?,
    respuestaCorrecta: Boolean,
    modifier: Modifier = Modifier
) {
    if (respuestaElegida == null) return

    val acierto = respuestaElegida == respuestaCorrecta

    val texto = when {
        respuestaElegida && respuestaCorrecta ->
            stringResource(R.string.has_elegido_verdadero_y_la_respuesta_era_verdadero_correcto)

        respuestaElegida && !respuestaCorrecta ->
            stringResource(R.string.has_elegido_verdadero_y_la_respuesta_era_falso_incorrecto)

        !respuestaElegida && respuestaCorrecta ->
            stringResource(R.string.has_elegido_falso_y_la_respuesta_era_verdadero_incorrecto)

        else ->
            stringResource(R.string.has_elegido_falso_y_la_respuesta_era_falso_correcto)
    }

    Text(
        text = texto,
        modifier = modifier,
        textAlign = TextAlign.Center,
        fontSize = 24.sp,
        color = if (acierto) Color.Green else Color.Red
    )
}


@Preview
@Composable
fun preTexto() {
    TextoRespuesta( false,true)
}