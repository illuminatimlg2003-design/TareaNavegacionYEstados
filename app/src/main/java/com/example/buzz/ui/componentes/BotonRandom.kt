package com.example.buzz.ui.componentes

import android.R
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

@Composable
fun BotonRandom( modifier: Modifier = Modifier, onClick: () -> Unit  ) {
    Button(modifier = modifier,
        contentPadding = PaddingValues(
            horizontal = 4.dp,
            vertical = 8.dp
        ),
        onClick = onClick
    ) {


        Text(stringResource(com.example.buzz.R.string.random))
        Icon(
            painter = painterResource(R.drawable.ic_menu_help),
            contentDescription = null
        )

    }
}
@Preview
@Composable
fun prerandom(){
    Row() {
        BotonRandom(
            modifier = TODO(),
            onClick = TODO()
        )


    }

}