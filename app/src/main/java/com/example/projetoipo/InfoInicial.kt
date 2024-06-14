package com.example.projetoipo

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import com.example.projetoipo.databinding.ActivityInfoInicialBinding
import java.util.Calendar

class InfoInicial : AppCompatActivity() {

    private lateinit var binding: ActivityInfoInicialBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        binding = ActivityInfoInicialBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val retornoGraduacaoNome = intent.getStringExtra("graduacaoNome")
        val retornoCrbm = intent.getStringExtra("crbm")
        val retornoObm = intent.getStringExtra("obm")

        binding.txtGraducaoNome.text = retornoGraduacaoNome

        binding.btnCalendar.setOnClickListener {
            showDatePickerDialog()
        }

        binding.btnTimePicker.setOnClickListener {
            timePicker()
        }

        val itemNatureza = resources.getStringArray(R.array.naturezaEvento)
        val adapterNatureza = ArrayAdapter(this, android.R.layout.simple_list_item_1, itemNatureza)
        binding.autoNatureza.setAdapter(adapterNatureza)

        binding.autoNatureza.addTextChangedListener {

            val respNatureza = binding.autoNatureza.text.toString()

            when (respNatureza) {
                "Acidente de Trânsito" -> {
                    val itemAcidenteTransito =
                        resources.getStringArray(R.array.subNaturezaAcidenteTransito)
                    val adapterAcidenteTransito = ArrayAdapter(
                        this,
                        android.R.layout.simple_list_item_1,
                        itemAcidenteTransito
                    )
                    binding.autoSubnatureza.setAdapter(adapterAcidenteTransito)
                }

                "APH" -> {
                    val itemAph = resources.getStringArray(R.array.subNaturezaAPH)
                    val adapterAph =
                        ArrayAdapter(this, android.R.layout.simple_list_item_1, itemAph)
                    binding.autoSubnatureza.setAdapter(adapterAph)
                }

                "Atendimento Comunitário" -> {
                    val itemComunitario =
                        resources.getStringArray(R.array.subNaturezaAtendimentoComunitario)
                    val adapterComunitario =
                        ArrayAdapter(this, android.R.layout.simple_list_item_1, itemComunitario)
                    binding.autoSubnatureza.setAdapter(adapterComunitario)
                }

                "Busca e Salvamento" -> {
                    val itemBuscaSalvamento =
                        resources.getStringArray(R.array.subNaturezaBuscaSalvamento)
                    val adapterBuscaSalvamento =
                        ArrayAdapter(this, android.R.layout.simple_list_item_1, itemBuscaSalvamento)
                    binding.autoSubnatureza.setAdapter(adapterBuscaSalvamento)
                }

                "Desastre" -> {
                    val itemDesastre = resources.getStringArray(R.array.subNaturezaDesastre)
                    val adapterDesatre =
                        ArrayAdapter(this, android.R.layout.simple_list_item_1, itemDesastre)
                    binding.autoSubnatureza.setAdapter(adapterDesatre)
                }

                "Incêndio" -> {
                    val itemIncendio = resources.getStringArray(R.array.subNaturezaIncendio)
                    val adapterIncendio =
                        ArrayAdapter(this, android.R.layout.simple_list_item_1, itemIncendio)
                    binding.autoSubnatureza.setAdapter(adapterIncendio)
                }
            }
        }

        binding.txtData.addTextChangedListener(autoCompleteTextWatcher)
        binding.txtHoras.addTextChangedListener(autoCompleteTextWatcher)
        binding.autoNatureza.addTextChangedListener(autoCompleteTextWatcher)
        binding.autoSubnatureza.addTextChangedListener(autoCompleteTextWatcher)

        binding.btnAvancar.setOnClickListener {

            val natureza = binding.autoNatureza.text.toString()
            val subNatureza = binding.autoSubnatureza.text.toString()
            val data = binding.txtData.text.toString()
            val hora = binding.txtHoras.text.toString()

            val intent = Intent(this, Endereco::class.java)
            intent.putExtra("graduacaoNome", retornoGraduacaoNome)
            intent.putExtra("crbm", retornoCrbm)
            intent.putExtra("obm", retornoObm)

            intent.putExtra("data", data)
            intent.putExtra("hora", hora)
            intent.putExtra("natureza", natureza)
            intent.putExtra("subNatureza", subNatureza)


            startActivity(intent)
        }
    }

    private val autoCompleteTextWatcher = object: TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            val data = binding.txtData.text.toString()
            val hora = binding.txtHoras.text.toString()
            val natureza = binding.autoNatureza.text.toString()
            val subNatureza = binding.autoSubnatureza.text.toString()

            binding.btnAvancar.isEnabled = data.isNotEmpty() && hora.isNotEmpty() && natureza.isNotEmpty() && subNatureza.isNotEmpty()
        }

        override fun afterTextChanged(s: Editable?) {
        }
    }

    private fun showDatePickerDialog() {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(
            this,
            { _, selectedYear, selectedMonth, selectedDay ->
                val selectedDate = "$selectedDay/${selectedMonth + 1}/$selectedYear"

                binding.txtData.text = selectedDate
            },
            year, month, day
        )
        datePickerDialog.show()
    }

    private fun timePicker() {
        val calendar = Calendar.getInstance()
        val hour = calendar.get(Calendar.HOUR_OF_DAY)
        val minute = calendar.get(Calendar.MINUTE)

        val timePickerDialog = TimePickerDialog(
            this,
            { _, selectedHour, selectedMinute ->
                val selectedTime = "$selectedHour:$selectedMinute"
                binding.txtHoras.text = selectedTime
            },
            hour, minute, true
        )
        timePickerDialog.show()
    }

}