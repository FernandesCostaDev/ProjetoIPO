package com.example.projetoipo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.projetoipo.databinding.ActivityApoioBinding
import com.example.projetoipo.model.AdapterApoio
import com.example.projetoipo.model.ItemApoio

class Apoio : AppCompatActivity() {
    private lateinit var binding: ActivityApoioBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityApoioBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val apoioList = listOf(
            ItemApoio("Aeronáutica"),
            ItemApoio("Bombeiro Privado"),
            ItemApoio("Bombeiro Voluntário"),
            ItemApoio("CIA de Água e Esgoto"),
            ItemApoio("BPMOA/PMPR"),
            ItemApoio("CIA de Energia Elétrica"),
            ItemApoio("CB de Outro Estado"),
            ItemApoio("Defesa Civil (Casa Militar)"),
            ItemApoio("Defesa Civil (COMPDEC)"),
            ItemApoio("Concessionária da Rodovia"),
            ItemApoio("CREA"),
            ItemApoio("DER/DNIT"),
            ItemApoio("Exército"),
            ItemApoio("Fiscalização de Trânsito Municipal"),
            ItemApoio("Guarda Municipal"),
            ItemApoio("Guarda Portuária"),
            ItemApoio("IAP"),
            ItemApoio("IBAMA"),
            ItemApoio("IML"),
            ItemApoio("Instituição Privada"),
            ItemApoio("Instituição Pública"),
            ItemApoio("IPEM"),
            ItemApoio("Marinha"),
            ItemApoio("PMPR"),
            ItemApoio("Polícia Científica Paraná"),
            ItemApoio("Polícia Civil"),
            ItemApoio("Polícia Federal"),
            ItemApoio("Polícia Florestal"),
            ItemApoio("Polícia Militar de Outro Estado"),
            ItemApoio("Polícia Rodoviária Federal"),
            ItemApoio("Receita Estadual"),
            ItemApoio("Vigilância Sanitária Municipal"),
            ItemApoio("Vigilância Sanitária Estadual"),
            ItemApoio("Polícia Rodoviária Estadual"),
            ItemApoio("Prefeitura Municipal"),
            ItemApoio("SAMU"),
            ItemApoio("Não houve")
        )

        val adapter = AdapterApoio(apoioList, this)
        binding.rv.layoutManager = LinearLayoutManager(this)
        binding.rv.adapter = adapter

        binding.btnAvancar.setOnClickListener {
            val selectedItems = apoioList.filter { it.isChecked }.map { it.item }
            binding.txtApoio.text = selectedItems.joinToString(", ")
        }
    }
}
