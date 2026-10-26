package com.example.buzz.ui.componentes



import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.buzz.R


@Composable
fun BotonMovimiento(esSiguiente: Boolean, modifier: Modifier = Modifier, onClick: () -> Unit) {
    Button(modifier = modifier,contentPadding = PaddingValues(
        horizontal = 4.dp,
        vertical = 8.dp
    ),
        onClick = onClick
    ) {
        if (!esSiguiente) {
            Icon(
                painter = painterResource(android.R.drawable.ic_media_rew),
                contentDescription = ""
            )

        }

        Text(
            if (esSiguiente) stringResource(R.string.siguiente) else stringResource(R.string.anterior),
            maxLines = 1,
            fontSize = 12.sp,
            softWrap = false,
        )

        if (esSiguiente) {

            Icon(
                painter = painterResource(android.R.drawable.ic_media_ff),
                contentDescription = ""
            )
        }
    }
}
@Preview
@Composable
fun Pre2(){
    Row() {
        BotonMovimiento(
            false,
            onClick = TODO()
        )
        BotonMovimiento(
            true,
            onClick = TODO()
        )


    }

}