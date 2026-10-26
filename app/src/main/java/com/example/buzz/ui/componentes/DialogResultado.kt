package com.example.buzz.ui.componentes
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.TextButton
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.example.buzz.R
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp


@Composable
fun DialogResultado(
    aciertos: Int,
    totalPreguntas: Int,
    onCerrar: () -> Unit

) {
    val porcentaje = if (totalPreguntas > 0) {
        aciertos * 100f / totalPreguntas
    } else {
        0f
    }

    val (mensaje, imagen) = when {
        aciertos == totalPreguntas && totalPreguntas > 0 -> {
            Pair(
                stringResource(R.string.eres_un_verdadero_chad_soldado_de_saselandia),
                R.drawable.respuestaperfecta
            )
        }

        porcentaje >= 50f -> {
            Pair(
                stringResource(R.string.mid_como_one_piece),
                R.drawable.respuestamid
            )
        }

        else -> {
            Pair(
                stringResource(R.string.se_acab_para_ti_hermano_empieza_a_ropemaxxear),
                R.drawable.itsover
            )
        }
    }
    AlertDialog(
        onDismissRequest = onCerrar,
        title = {
            Text(stringResource(R.string.resultado_de_tu_examen))
        },
        text = {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = imagen),
                    contentDescription = null,
                    modifier = Modifier.size(180.dp)
                )

                Text(
                    text = mensaje,
                    textAlign = TextAlign.Center,
                    fontSize = 22.sp
                )

                Text(
                    text = stringResource(
                        R.string.porcentaje_de_aciertos,
                        String.format(
                            java.util.Locale.getDefault(),
                            "%.2f",
                            porcentaje
                        )
                    ),
                    fontSize = 22.sp,
                    textAlign = TextAlign.Center
                )
            }
        },
        confirmButton = {
            TextButton(onClick = onCerrar) {
                Text(stringResource(R.string.salir))
            }
        }
    )
}