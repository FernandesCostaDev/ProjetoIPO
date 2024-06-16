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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVitimaBinding.inflate(layoutInflater)
        setContentView(binding.root)

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
            intent = Intent(this,Resumo::class.java)

            intent.putExtra("vitIlesa", vitIlesa)

            Toast.makeText(this,"Vitima Ilesa: $vitIlesa",Toast.LENGTH_LONG).show()
            startActivity(intent)
        }
    }

    private fun updateTotalAndButtonState() {
        vitIlesa = binding.edtIlesa.text.toString().toIntOrNull() ?: 0
        vitCod1 = binding.edtCod1.text.toString().toIntOrNull() ?: 0
        vitCod2 = binding.edtCod2.text.toString().toIntOrNull() ?: 0
        vitCod3 = binding.edtCod3.text.toString().toIntOrNull() ?: 0
        vitCod4 = binding.edtCod4.text.toString().toIntOrNull() ?: 0

        val totalVit = vitIlesa + vitCod1 + vitCod2 + vitCod3 + vitCod4
        binding.txtTotalVitima.text = totalVit.toString()

        binding.btnAvancar.isEnabled = totalVit > 0
    }
}