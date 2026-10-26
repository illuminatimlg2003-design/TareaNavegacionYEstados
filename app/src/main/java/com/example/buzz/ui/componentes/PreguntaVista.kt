package com.example.buzz.ui.componentes

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.buzz.R


@Composable

fun PreguntaVista(
    pregunta: String,
    imagen: Int?,
    modifier: Modifier = Modifier,
    modifierImagen: Modifier= Modifier
) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = pregunta,
            textAlign = TextAlign.Center,
            fontSize = 30.sp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        )

        Image(
            painter = painterResource(
                id = imagen?.takeIf { it != 0 } ?: R.drawable.ping
            ),
            contentDescription = stringResource(R.string.imagen_de_la_pregunta),
            modifier = modifierImagen
                .weight(1f)
                .fillMaxWidth()
                .padding(10.dp),
            contentScale = ContentScale.FillBounds
        )
    }
}

@Preview
@Composable
fun pre3() {
    PreguntaVista( "Esto se ve", 0)
 }