
package com.example.buzz.data

import android.content.Context
import com.example.buzz.R
import com.example.buzz.model.Pregunta
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream

val preguntasIniciales = listOf(
    //estas strings tambien van hardcodeadas o petan la app
    Pregunta("¿He jugado al lol lo equivalente a dos ciclos profesionales y medio?", true, R.drawable.pregunta1),
    Pregunta("El mod LEFTOVERS es ingenioso y resuelve un problema estructural de minecraft™?", true, R.drawable.pregunta2),
    Pregunta("El outro de Steel ball run es lowkey tuff?", false, R.drawable.pregunta3),
    Pregunta("¿For honor lo ponen gratis a veces porque el precio a pagar en realidad es tu alma?", true, R.drawable.pregunta4),
    Pregunta("¿Invertir 20€ en Salvar el mundo en 2017 es como haber invertido en bitcoin en 2010?", true, R.drawable.pregunta5),
    Pregunta("¿En magic the gathering™ los jugadores de azul son personas?", false, R.drawable.pregunta6),
    Pregunta("¿Deadlock™ apunta a ser el nuevo moba de referencia aglutinando tanto a jugadores de MOBAS como shooters?", true, R.drawable.pregunta7) ,
    Pregunta("¿Clavicular fue framemoggeado por el ASU frat leader?", true, R.drawable.pregunta8)

)


fun inicializar(context: Context) {
    val archivo = File(context.filesDir, "preguntas.txt")

    if (!archivo.exists() || archivo.length() == 0L) {
        preguntasIniciales.forEach { pregunta ->
            guardarPregunta(context, pregunta)
        }
    }
}

fun guardarPregunta(context: Context, pregunta: Pregunta) {
    val archivo = File(context.filesDir, "preguntas.txt")

    FileOutputStream(archivo, true).bufferedWriter().use { writer ->
        writer.write(
            "${pregunta.pregunta};${pregunta.respuesta};${pregunta.imagen ?: 0}"
        )
        writer.newLine()
    }
}
fun cargarPreguntas(context: Context): List<Pregunta> {
    val archivo = File(context.filesDir, "preguntas.txt")

    if (!archivo.exists()) return emptyList()

    return FileInputStream(archivo).bufferedReader().useLines { lineas ->
        lineas.mapNotNull { linea ->
            val partes = linea.split(";")

            if (partes.size != 3) return@mapNotNull null

            Pregunta(
                pregunta = partes[0],
                respuesta = partes[1].toBoolean(),
                imagen = partes[2].toInt()
            )
        }.toList()
    }
}