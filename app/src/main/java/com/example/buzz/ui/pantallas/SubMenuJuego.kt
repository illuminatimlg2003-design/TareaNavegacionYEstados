package com.example.buzz.ui.pantallas

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import com.example.buzz.R
import com.example.buzz.navegation.ExamenNavegacion
import com.example.buzz.navegation.QuizNavegacion
import com.example.buzz.navegation.SpeedrunNavegacion
import com.example.buzz.navegation.StatsNavegacion


@Composable
fun SubMenuJuego(pila: NavBackStack<NavKey>, sumarTap: () -> Unit) {
    val opcionesMenu = listOf(
        stringResource(R.string.quiz), stringResource(R.string.speedrun),
        stringResource(R.string.examen)
    )


    Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {
        for (i in 0..2) {
            Button(modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
                contentPadding = PaddingValues(
                    horizontal = 4.dp,
                    vertical = 8.dp
                ),
                onClick = {
                    sumarTap()
                    when (i) {
                        0 -> pila.add(QuizNavegacion)
                        1 -> pila.add(SpeedrunNavegacion)
                        2 -> pila.add(ExamenNavegacion)
                    }

                }
            ) {

                Text(opcionesMenu[i])


            }
        }


    }
}



@Preview(showBackground = true)
@Composable
fun PreviewsubMenu2() {

}