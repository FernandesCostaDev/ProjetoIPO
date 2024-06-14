package com.example.projetoipo

import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.example.projetoipo.databinding.ActivityEnderecoBinding

class Endereco : AppCompatActivity() {

    private lateinit var binding: ActivityEnderecoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        binding = ActivityEnderecoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val retornoGraduacaoNome = intent.getStringExtra("graduacaoNome")
        val retornoCrbm = intent.getStringExtra("crbm")
        val retornoObm = intent.getStringExtra("obm")
        val retornoData = intent.getStringExtra("data")
        val retornoHora = intent.getStringExtra("hora")
        val retornoNatureza = intent.getStringExtra("natureza")
        val retornoSubNatureza = intent.getStringExtra("subNatureza")

        val itemCidades = resources.getStringArray(R.array.cidades)
        val adapterCidades = ArrayAdapter(this, android.R.layout.simple_list_item_1, itemCidades)
        binding.autoCidades.setAdapter(adapterCidades)

        val itemLogradouros = resources.getStringArray(R.array.logradouros)
        val adapterLogradouros = ArrayAdapter(this, android.R.layout.simple_list_item_1, itemLogradouros)
        binding.autoLogradouro.setAdapter(adapterLogradouros)

        binding.autoCidades.addTextChangedListener(autoCompleteTextWatcher)
        binding.autoLogradouro.addTextChangedListener(autoCompleteTextWatcher)
        binding.edtBairro.addTextChangedListener(autoCompleteTextWatcher)

        binding.btnAvancar.setOnClickListener {

            val cidade = binding.autoCidades.text.toString()
            val logradouro = binding.autoLogradouro.text.toString()
            val bairro = binding.edtBairro.text.toString()
            val complemento = binding.edtComplemento.text.toString()

            val intent = Intent(this, Recursos::class.java)

            intent.putExtra("graduacaoNome", retornoGraduacaoNome)
            intent.putExtra("crbm", retornoCrbm)
            intent.putExtra("obm", retornoObm)
            intent.putExtra("data", retornoData)
            intent.putExtra("hora", retornoHora)
            intent.putExtra("natureza", retornoNatureza)
            intent.putExtra("subNatureza", retornoSubNatureza)

            intent.putExtra("cidade", cidade)
            intent.putExtra("logradouro", logradouro)
            intent.putExtra("bairro", bairro)
            intent.putExtra("complemento", complemento)
            startActivity(intent)
        }
    }

    private val autoCompleteTextWatcher = object: TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            val cidade = binding.autoCidades.text
            val logradouro = binding.autoLogradouro.text
            val bairro = binding.edtBairro.text

            binding.btnAvancar.isEnabled = cidade.isNotEmpty() && logradouro.isNotEmpty() && bairro.isNotEmpty()
        }

        override fun afterTextChanged(s: Editable?) {
        }
    }
}