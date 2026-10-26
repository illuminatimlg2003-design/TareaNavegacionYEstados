package com.example.buzz.ui.componentes

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layout
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.buzz.R


@Composable
fun BotonRespuesta(
    tipo:Boolean,  modifier: Modifier = Modifier, onClick: () -> Unit

) {
    Button( modifier = modifier,
        colors =  ButtonColors(
            Color.Blue,
            contentColor = Color.White,
            disabledContainerColor = Color.Black,
            disabledContentColor = Color.Black
        ),
        onClick = onClick



    ) {

            Text(if (tipo) stringResource(R.string.verdadero) else stringResource(R.string.falso))

    }
}
@Preview
@Composable
fun pre(){
    Row() {

        BotonRespuesta(
            false,
            modifier = TODO(),
            onClick = TODO(),
        )
        BotonRespuesta(
            true,
            modifier = TODO(),
            onClick = TODO(),
        )
    }

}