package com.example.buzz.ui.pantallas

import android.R.attr.onClick
import android.widget.Button
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.buzz.R
import com.example.buzz.data.cargarPreguntas
import com.example.buzz.ui.componentes.BotonMovimiento
import com.example.buzz.ui.componentes.BotonRandom
import com.example.buzz.ui.componentes.BotonRespuesta
import com.example.buzz.ui.componentes.PreguntaVista
import com.example.buzz.ui.componentes.TextoRespuesta


@Composable
fun Stats(respuestasCorrectas:Int,respuestasIncorrectas:Int,tapsTotales :Int,sumarTap: () -> Unit){
    val porcentajeAciertos = if (respuestasCorrectas+respuestasIncorrectas > 0) {
        respuestasCorrectas * 100f / (respuestasCorrectas+respuestasIncorrectas)
    } else {
        0f
    }
    val porcentajeError = 100 - porcentajeAciertos

    Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center,
    horizontalAlignment = Alignment.CenterHorizontally) {
    Text(stringResource(R.string.taps_totales, tapsTotales),fontSize = 24.sp)
    Text(stringResource(R.string.preguntas_totales) +(respuestasCorrectas+respuestasIncorrectas) ,fontSize = 24.sp)
        Text(
            stringResource(
                R.string.respuestas_correctas,
                respuestasCorrectas,
                String.format("%.2f", porcentajeAciertos)
            ),
            fontSize = 24.sp)
        Text(
            stringResource(
                R.string.respuestas_incorrectas,
                respuestasIncorrectas,
                String.format("%.2f", porcentajeError)
            ),
            fontSize = 24.sp)
        Button(modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(
                horizontal = 4.dp,
                vertical = 50.dp
            ),
            onClick = sumarTap



        ) {

            Text(stringResource(R.string.farmear_taps))

        }
    
    
}

}



@Preview(showBackground = true)
@Composable
fun PreviewsTATS() {
    Stats(
        3, 4, 9,
        sumarTap = TODO(),
    )
}

 
