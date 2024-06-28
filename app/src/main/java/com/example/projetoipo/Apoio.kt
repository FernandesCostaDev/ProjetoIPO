package com.example.projetoipo

import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.projetoipo.databinding.ActivityApoioBinding

class Apoio : AppCompatActivity() {
    private lateinit var binding: ActivityApoioBinding
    private val selectedValues = mutableListOf<String>()
    private val maxSelection = 5
    private var selecaoApoio = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityApoioBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val retornoGraduacaoNome = intent.getStringExtra("graduacaoNome").toString()
        val retornoCrbm = intent.getStringExtra("crbm").toString()
        val retornoObm = intent.getStringExtra("obm").toString()
        val retornoData = intent.getStringExtra("data").toString()
        val retornoHora = intent.getStringExtra("hora").toString()
        val retornoNatureza = intent.getStringExtra("natureza").toString()
        val retornoSubNatureza = intent.getStringExtra("subNatureza").toString()
        val retornoCidade = intent.getStringExtra("cidade").toString()
        val retornoLogradouro = intent.getStringExtra("logradouro").toString()
        val retornoBairro = intent.getStringExtra("bairro").toString()
        val retornoComplemento = intent.getStringExtra("complemento").toString()
        val retornoCbAcionado = intent.getStringExtra("cbAcionado").toString()
        val retornoVtrEmpenhada = intent.getStringExtra("vtrEmpenhada").toString()
        val retornoEfetivo = intent.getStringExtra("efetivo").toString()

        val retornoVitIlesa = intent.getIntExtra("vitIlesa", 0)
        val retornoVitCod1 = intent.getIntExtra("vitCod1", 0)
        val retornoVitCod2 = intent.getIntExtra("vitCod2", 0)
        val retornoVitCod3 = intent.getIntExtra("vitCod3",0)
        val retornoVitCod4 = intent.getIntExtra("vitCod4", 0)
        val retornoTotalVit = intent.getIntExtra("totalVit",0)
        val retornoObsservacaoVit = intent.getStringExtra("obsservacaoVit").toString()

        val retornoMeioAmbiente = intent.getStringExtra("meioAmbiente").toString()
        val retornoDanosPropriedade = intent.getStringExtra("danosPropriedade").toString()
        val retornoCenario = intent.getStringExtra("cenario").toString()
        val retornoDesdobramento = intent.getStringExtra("desdobramento").toString()

        val checkBoxes = listOf(
            binding.ckBox1, binding.ckBox2, binding.ckBox3, binding.ckBox4, binding.ckBox5,
            binding.ckBox6, binding.ckBox7, binding.ckBox8, binding.ckBox10,
            binding.ckBox11, binding.ckBox12, binding.ckBox13, binding.ckBox14, binding.ckBox15,
            binding.ckBox16, binding.ckBox17, binding.ckBox18, binding.ckBox19, binding.ckBox20,
            binding.ckBox21, binding.ckBox22, binding.ckBox23, binding.ckBox24, binding.ckBox25,
            binding.ckBox26, binding.ckBox27, binding.ckBox28, binding.ckBox29, binding.ckBox30,
            binding.ckBox31, binding.ckBox32, binding.ckBox33, binding.ckBox34, binding.ckBox35,
        )

        for (checkBox in checkBoxes) {
            checkBox.setOnCheckedChangeListener { buttonView, isChecked ->
                val value = buttonView.text.toString()
                if (isChecked) {
                    if (selectedValues.size < maxSelection) {
                        selectedValues.add(value)
                    } else {
                        buttonView.isChecked = false
                        Toast.makeText(this, "Você pode selecionar no máximo $maxSelection itens.", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    selectedValues.remove(value)
                }
                updateTextView()
            }
        }

        binding.txtApoio.addTextChangedListener(autoCompleteTextWatcher)

        binding.btnAvancar.setOnClickListener {

            val intent = Intent(this, Resumo::class.java)
            intent.putExtra("graduacaoNome", retornoGraduacaoNome)
            intent.putExtra("crbm", retornoCrbm)
            intent.putExtra("obm", retornoObm)
            intent.putExtra("data", retornoData)
            intent.putExtra("hora", retornoHora)
            intent.putExtra("natureza", retornoNatureza)
            intent.putExtra("subNatureza", retornoSubNatureza)
            intent.putExtra("cidade", retornoCidade)
            intent.putExtra("logradouro", retornoLogradouro)
            intent.putExtra("bairro", retornoBairro)
            intent.putExtra("complemento", retornoComplemento)
            intent.putExtra("cbAcionado", retornoCbAcionado)
            intent.putExtra("vtrEmpenhada", retornoVtrEmpenhada)
            intent.putExtra("efetivo", retornoEfetivo)

            intent.putExtra("vitIlesa", retornoVitIlesa)
            intent.putExtra("vitCod1", retornoVitCod1)
            intent.putExtra("vitCod2", retornoVitCod2)
            intent.putExtra("vitCod3", retornoVitCod3)
            intent.putExtra("vitCod4", retornoVitCod4)
            intent.putExtra("totalVit", retornoTotalVit)

            intent.putExtra("obsservacaoVit", retornoObsservacaoVit)
            intent.putExtra("meioAmbiente", retornoMeioAmbiente)
            intent.putExtra("danosPropriedade", retornoDanosPropriedade)
            intent.putExtra("cenario", retornoCenario)
            intent.putExtra("desdobramento", retornoDesdobramento)

            intent.putExtra("selecaoApoio",selecaoApoio)
            startActivity(intent)
        }
    }

    private fun updateTextView() {
        selecaoApoio = selectedValues.joinToString(", ")
        binding.txtApoio.text = selectedValues.joinToString(", ")
    }

    private val autoCompleteTextWatcher = object: TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            binding.btnAvancar.isEnabled = selecaoApoio.isNotEmpty()
        }

        override fun afterTextChanged(s: Editable?) {
        }
    }
}

