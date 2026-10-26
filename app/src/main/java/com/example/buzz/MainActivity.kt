package com.example.buzz

import android.media.MediaPlayer
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation3.runtime.rememberNavBackStack
import com.example.buzz.data.inicializar
import com.example.buzz.navegation.MenuNavegacion
import com.example.buzz.navegation.Navegacion

class MainActivity : ComponentActivity() {

    private val preferencias by lazy {
        getSharedPreferences("estadisticas", MODE_PRIVATE)
    }

    private var respuestasCorrectas by mutableIntStateOf(0)
    private var respuestasIncorrectas by mutableIntStateOf(0)
    private var tapsTotales by mutableIntStateOf(0)

    private var musicaFondo: MediaPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        inicializar(this)
        musicaFondo = MediaPlayer.create(this, R.raw.musica)?.apply {
            isLooping = true
            setVolume(0.5f, 0.5f)
        }
        respuestasCorrectas =
            preferencias.getInt("respuestasCorrectas", 0)

        respuestasIncorrectas =
            preferencias.getInt("respuestasIncorrectas", 0)

        tapsTotales =
            preferencias.getInt("tapsTotales", 0)

        setContent {
            val pila = rememberNavBackStack(MenuNavegacion)

            Navegacion(
                pila = pila,
                respuestasCorrectas = respuestasCorrectas,
                respuestasIncorrectas = respuestasIncorrectas,
                tapsTotales = tapsTotales,
                sumarCorrecta = { respuestasCorrectas++ },
                sumarIncorrecta = { respuestasIncorrectas++ },
                sumarTap = { tapsTotales++ }
            )
        }
    }

    override fun onStop() {
        super.onStop()
        musicaFondo?.pause()

        preferencias.edit()
            .putInt("respuestasCorrectas", respuestasCorrectas)
            .putInt("respuestasIncorrectas", respuestasIncorrectas)
            .putInt("tapsTotales", tapsTotales)
            .apply()
    }
    override fun onStart() {
        super.onStart()
        musicaFondo?.let {
            if (!it.isPlaying) {
                it.start()
            }
        }
    }



    override fun onDestroy() {
        musicaFondo?.release()
        musicaFondo = null
        super.onDestroy()
    }
}
