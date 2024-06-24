package com.example.projetoipo

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.projetoipo.databinding.ActivityVitimaBinding
class Vitima : AppCompatActivity() {
    private lateinit var binding: ActivityVitimaBinding

    private var vitIlesa: Int = 0
    private var vitCod1: Int = 0
    private var vitCod2: Int = 0
    private var vitCod3: Int = 0
    private var vitCod4: Int = 0
    private var totalVit: Int = 0
    private var observacaoVit: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVitimaBinding.inflate(layoutInflater)
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

        val editTexts = listOf(binding.edtIlesa, binding.edtCod1, binding.edtCod2, binding.edtCod3, binding.edtCod4)

        val textWatcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                updateTotalAndButtonState()
            }

            override fun afterTextChanged(s: Editable?) {}
        }

        editTexts.forEach { editText ->
            editText.addTextChangedListener(textWatcher)
        }

        binding.btnAvancar.setOnClickListener {

           vitIlesa = binding.edtIlesa.text.toString().toIntOrNull() ?: 0
           vitCod1 = binding.edtCod1.text.toString().toIntOrNull() ?: 0
           vitCod2 = binding.edtCod2.text.toString().toIntOrNull() ?: 0
           vitCod3 = binding.edtCod3.text.toString().toIntOrNull() ?: 0
           vitCod4 = binding.edtCod4.text.toString().toIntOrNull() ?: 0
           totalVit = binding.txtTotalVitima.text.toString().toIntOrNull() ?: 0
           observacaoVit = binding.editObsVitima.text.toString()

            intent = Intent(this,Descritivo::class.java)

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
            intent.putExtra("cbAcionado",retornoCbAcionado)
            intent.putExtra("vtrEmpenhada",retornoVtrEmpenhada)
            intent.putExtra("efetivo",retornoEfetivo)

            intent.putExtra("vitIlesa", vitIlesa)
            intent.putExtra("vitCod1", vitCod1)
            intent.putExtra("vitCod2", vitCod2)
            intent.putExtra("vitCod3", vitCod3)
            intent.putExtra("vitCod4", vitCod4)
            intent.putExtra("totalVit", totalVit)
            intent.putExtra("observacaoVit", observacaoVit)

            startActivity(intent)
        }
    }

    private fun updateTotalAndButtonState() {
        vitIlesa = binding.edtIlesa.text.toString().toIntOrNull() ?: 0
        vitCod1 = binding.edtCod1.text.toString().toIntOrNull() ?: 0
        vitCod2 = binding.edtCod2.text.toString().toIntOrNull() ?: 0
        vitCod3 = binding.edtCod3.text.toString().toIntOrNull() ?: 0
        vitCod4 = binding.edtCod4.text.toString().toIntOrNull() ?: 0

        totalVit = vitIlesa + vitCod1 + vitCod2 + vitCod3 + vitCod4
        binding.txtTotalVitima.text = totalVit.toString()

        binding.btnAvancar.isEnabled = totalVit > 0
    }
}