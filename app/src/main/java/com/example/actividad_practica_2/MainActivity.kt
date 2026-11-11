
package com.example.actividad_practica_2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.util.Locale

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Datos inmutables
        val nombreEstudiante: String = "Carlos Pérez"
        val carrera: String = "Ingeniería de Sistemas"
        val asignatura: String =
            "Programación para Dispositivos Móviles"

        // Notas mutables
        var notaCorte1: Double = 4.2
        var notaCorte2: Double = 3.5
        var notaCorte3: Double? = null

        // Operador Elvis para controlar valores nulos
        val notaFinal3: Double = notaCorte3 ?: 0.0

        // Cálculo del promedio
        val promedio = calcularPromedio(
            notaCorte1,
            notaCorte2,
            notaFinal3
        )

        // Generación del reporte
        val reporte = generarReporte(
            nombreEstudiante,
            carrera,
            asignatura,
            promedio
        )

        // Mostrar el resultado en Android
        setContent {
            MaterialTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    PantallaReporte(
                        reporte = reporte,
                        promedio = promedio
                    )
                }
            }
        }
    }
}

// Función con expresión única
fun calcularPromedio(
    n1: Double,
    n2: Double,
    n3: Double
): Double = (n1 + n2 + n3) / 3

// Función para generar el reporte
fun generarReporte(
    estudiante: String,
    carrera: String,
    materia: String,
    promedio: Double
): String {

    val estado = if (promedio >= 3.0) {
        "APROBADO"
    } else {
        "REPROBADO"
    }

    return """
        ESTUDIANTE: $estudiante

        CARRERA: $carrera

        ASIGNATURA: $materia

        PROMEDIO: ${String.format(Locale.US, "%.2f", promedio)}

        ESTADO: $estado
    """.trimIndent()
}

// Pantalla visual de la aplicación
@Composable
fun PantallaReporte(
    reporte: String,
    promedio: Double
) {

    val colorEstado = if (promedio >= 3.0) {
        Color(0xFF2E7D32)
    } else {
        Color(0xFFC62828)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = "REPORTE ACADÉMICO",
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )

        Spacer(modifier = Modifier.height(30.dp))

        Text(
            text = reporte.substringBefore("ESTADO:"),
            fontSize = 17.sp,
            lineHeight = 28.sp
        )

        Spacer(modifier = Modifier.height(15.dp))

        Text(
            text = "ESTADO: ${
                if (promedio >= 3.0) "APROBADO" else "REPROBADO"
            }",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color = colorEstado
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "Nota mínima para aprobar: 3.0",
            fontSize = 14.sp,
            color = Color.Gray
        )
    }
}
