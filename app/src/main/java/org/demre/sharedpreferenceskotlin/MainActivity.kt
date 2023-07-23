package org.demre.sharedpreferenceskotlin

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import org.demre.sharedpreferenceskotlin.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var msharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        msharedPreferences = getSharedPreferences("cookie", Context.MODE_PRIVATE)
        binding.btnGuardar.setOnClickListener {
            val texto = binding.etTexto.text.toString()
            val entero = binding.etEntero.text.toString().toInt()
            val decimal = binding.etDecimal.text.toString().toFloat()
            val boleano = binding.switch1.isChecked

            guardarDatos(texto, entero, decimal, boleano)


        }
        binding.btnMostrar.setOnClickListener {
            mostrarDatos()
        }
        binding.btnBorrar.setOnClickListener {
            borrarDatos()
        }


    }
    private fun guardarDatos(texto: String, entero: Int, decimal: Float, boleano: Boolean) {
        msharedPreferences.edit().putString("miTexto", texto).apply()
        msharedPreferences.edit().putInt("miEntero", entero).apply()
        msharedPreferences.edit().putFloat("miDecimal", decimal).apply()
        msharedPreferences.edit().putBoolean("miBoleano", boleano).apply()



    }
    private fun mostrarDatos() {
        val texto = msharedPreferences.getString("miTexto", "")
        val entero = msharedPreferences.getInt("miEntero", 0)
        val decimal = msharedPreferences.getFloat("miDecimal", 0.0f)
        val boleano = msharedPreferences.getBoolean("miBoleano", false)

        binding.tvTexto.text = texto
        binding.tvEntero.text = entero.toString()
        binding.tvDecimal.text = decimal.toString()
        binding.tvSwitch.text = boleano.toString()
        binding.switch1.isChecked = boleano
    }

    private fun borrarDatos() {
        binding.tvTexto.text = ""
        binding.tvEntero.text = ""
        binding.tvDecimal.text = ""
        binding.tvSwitch.text = ""

        binding.etTexto.text.clear()
        binding.etEntero.text.clear()
        binding.etDecimal.text.clear()
        binding.switch1.isChecked = false

        msharedPreferences.edit().clear().apply()
    }
}
