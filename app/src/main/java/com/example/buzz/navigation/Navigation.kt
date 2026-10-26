package com.example.buzz.navegation

import androidx.compose.runtime.Composable
 import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.ui.NavDisplay
import com.example.buzz.ui.pantallas.EscribirPregunta
import com.example.buzz.ui.pantallas.Examen

import com.example.buzz.ui.pantallas.Menu
import com.example.buzz.ui.pantallas.Quiz
import com.example.buzz.ui.pantallas.Speedrun
import com.example.buzz.ui.pantallas.Stats
import com.example.buzz.ui.pantallas.SubMenuJuego

@Composable
fun Navegacion(
    pila: NavBackStack<NavKey>,
    respuestasCorrectas: Int,
    respuestasIncorrectas: Int,
    tapsTotales: Int,
    sumarCorrecta: () -> Unit,
    sumarIncorrecta: () -> Unit,
    sumarTap: () -> Unit
) {
    NavDisplay(
        backStack = pila,
        onBack = {
            pila.removeLastOrNull()
        },
        entryProvider = entryProvider {
            entry<MenuNavegacion> {
                Menu(pila,sumarTap)
            }
            entry<SubmenuJuegoNavegacion> {
                SubMenuJuego(pila,sumarTap)
            }

            entry<QuizNavegacion> {
                Quiz(sumarIncorrecta,sumarCorrecta, sumarTap )
            }
            entry<StatsNavegacion> {
                Stats(   respuestasCorrectas,respuestasIncorrectas,tapsTotales,sumarTap)
            }
            entry<EscribirNavegacion> {
                EscribirPregunta(sumarTap)
            }
            entry< SpeedrunNavegacion> {
                Speedrun(sumarIncorrecta , sumarCorrecta, sumarTap)
            }
            entry< ExamenNavegacion> {
                Examen(pila)
            }

        }
    )
}