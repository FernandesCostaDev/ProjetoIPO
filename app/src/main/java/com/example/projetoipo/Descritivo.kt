package com.example.projetoipo

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.projetoipo.databinding.ActivityDescritivoBinding

class Descritivo : AppCompatActivity() {
    private lateinit var binding: ActivityDescritivoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDescritivoBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.btnAvancar.setOnClickListener {
            val meioAmbiente = binding.edtDanosMeioAmbiente.text.toString()

            val intent = Intent(this, Resumo::class.java)

            intent.putExtra("meioAmbiente", meioAmbiente)

            startActivity(intent)
        }
    }
}