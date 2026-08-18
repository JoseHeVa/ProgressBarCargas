package com.example.progressbarcargas

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var progressBar: ProgressBar
    private lateinit var tvPorcentaje: TextView
    private lateinit var btnIniciar: Button
    private val handler = Handler(Looper.getMainLooper())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        progressBar = findViewById(R.id.progressBar)
        tvPorcentaje = findViewById(R.id.tvPorcentaje)
        btnIniciar = findViewById(R.id.btnIniciar)

        btnIniciar.setOnClickListener {
            simularProceso()
        }
    }

    private fun simularProceso() {
        btnIniciar.isEnabled = false
        progressBar.visibility = View.VISIBLE
        progressBar.progress = 0
        tvPorcentaje.text = "0%"

        // Etapa 1: 30%
        handler.postDelayed({
            actualizarProgreso(30)

            // Etapa 2: 60%
            handler.postDelayed({
                actualizarProgreso(60)

                // Etapa 3: 100%
                handler.postDelayed({
                    actualizarProgreso(100)

                    // Al completar, se oculta la ProgressBar automáticamente
                    handler.postDelayed({
                        progressBar.visibility = View.GONE
                        btnIniciar.isEnabled = true
                    }, 500)

                }, 1000)
            }, 1000)
        }, 1000)
    }

    private fun actualizarProgreso(valor: Int) {
        progressBar.progress = valor
        tvPorcentaje.text = "$valor%"
    }

    override fun onDestroy() {
        super.onDestroy()
        handler.removeCallbacksAndMessages(null)
    }
}
