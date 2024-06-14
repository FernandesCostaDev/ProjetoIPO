package com.example.projetoipo

import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.ArrayAdapter
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.projetoipo.databinding.ActivityRecursosBinding
import com.example.projetoipo.model.AdapterViatura
import com.example.projetoipo.model.App
import com.example.projetoipo.model.OnListClickListener2
import com.example.projetoipo.model.Viaturas


class Recursos : AppCompatActivity(), OnListClickListener2 {

    private lateinit var binding: ActivityRecursosBinding
    private lateinit var lista: MutableList<Viaturas>
    private lateinit var adapter1: AdapterViatura
    private lateinit var rv: RecyclerView
    private lateinit var txtEmpenhoVtr: TextView
    private lateinit var cbAcionado: Array<String>
    private lateinit var adapterCbAcionado: ArrayAdapter<String>
    private var selectedOption: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        binding = ActivityRecursosBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var cod: Int = 0
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

        lista = mutableListOf()

        when (retornoObm) {
            "1º GB Curitiba" -> {
                cbAcionado = resources.getStringArray(R.array.gbCuritiba)
                adapterCbAcionado =
                    ArrayAdapter(this, android.R.layout.simple_list_item_1, cbAcionado)
                binding.autoCbAcionado.setAdapter(adapterCbAcionado)
            }

            "2º GB Ponta Grossa" -> {
                cbAcionado = resources.getStringArray(R.array.gbPontaGrossa)
                adapterCbAcionado =
                    ArrayAdapter(this, android.R.layout.simple_list_item_1, cbAcionado)
                binding.autoCbAcionado.setAdapter(adapterCbAcionado)
            }

            "3º GB Londrina" -> {
                cbAcionado = resources.getStringArray(R.array.gbLondrina)
                adapterCbAcionado =
                    ArrayAdapter(this, android.R.layout.simple_list_item_1, cbAcionado)
                binding.autoCbAcionado.setAdapter(adapterCbAcionado)
            }

            "4º GB Cascavel" -> {
                cbAcionado = resources.getStringArray(R.array.gbCascavel)
                adapterCbAcionado =
                    ArrayAdapter(this, android.R.layout.simple_list_item_1, cbAcionado)
                binding.autoCbAcionado.setAdapter(adapterCbAcionado)
            }

            "5º GB Maringá" -> {
                cbAcionado = resources.getStringArray(R.array.gbMaringa)
                adapterCbAcionado =
                    ArrayAdapter(this, android.R.layout.simple_list_item_1, cbAcionado)
                binding.autoCbAcionado.setAdapter(adapterCbAcionado)
            }

            "6º GB São José dos Pinhais" -> {
                cbAcionado = resources.getStringArray(R.array.gbSaoJosePinhais)
                adapterCbAcionado =
                    ArrayAdapter(this, android.R.layout.simple_list_item_1, cbAcionado)
                binding.autoCbAcionado.setAdapter(adapterCbAcionado)
            }

            "7º GB Colombo" -> {
                cbAcionado = resources.getStringArray(R.array.gbColombo)
                adapterCbAcionado =
                    ArrayAdapter(this, android.R.layout.simple_list_item_1, cbAcionado)
                binding.autoCbAcionado.setAdapter(adapterCbAcionado)
            }

            "8º GB Paranaguá" -> {
                cbAcionado = resources.getStringArray(R.array.gbParanaguá)
                adapterCbAcionado =
                    ArrayAdapter(this, android.R.layout.simple_list_item_1, cbAcionado)
                binding.autoCbAcionado.setAdapter(adapterCbAcionado)
            }

            "9º GB Foz do Iguaçu" -> {
                cbAcionado = resources.getStringArray(R.array.gbFozIguaçu)
                adapterCbAcionado =
                    ArrayAdapter(this, android.R.layout.simple_list_item_1, cbAcionado)
                binding.autoCbAcionado.setAdapter(adapterCbAcionado)
            }

            "10º GB Francisco Beltrão" -> {
                cbAcionado = resources.getStringArray(R.array.gbFrancisco)
                adapterCbAcionado =
                    ArrayAdapter(this, android.R.layout.simple_list_item_1, cbAcionado)
                binding.autoCbAcionado.setAdapter(adapterCbAcionado)
            }

            "11º GB Apucarana" -> {
                cbAcionado = resources.getStringArray(R.array.gbApucarana)
                adapterCbAcionado =
                    ArrayAdapter(this, android.R.layout.simple_list_item_1, cbAcionado)
                binding.autoCbAcionado.setAdapter(adapterCbAcionado)
            }

            "12º GB Guarapuava" -> {
                cbAcionado = resources.getStringArray(R.array.gbGuarapuava)
                adapterCbAcionado =
                    ArrayAdapter(this, android.R.layout.simple_list_item_1, cbAcionado)
                binding.autoCbAcionado.setAdapter(adapterCbAcionado)
            }

            "13º GB Pato Branco" -> {
                cbAcionado = resources.getStringArray(R.array.gbPatoBranco)
                adapterCbAcionado =
                    ArrayAdapter(this, android.R.layout.simple_list_item_1, cbAcionado)
                binding.autoCbAcionado.setAdapter(adapterCbAcionado)
            }

            "1º SGBI Ivaiporã" -> {
                cbAcionado = resources.getStringArray(R.array.sgbiIvaipora)
                adapterCbAcionado =
                    ArrayAdapter(this, android.R.layout.simple_list_item_1, cbAcionado)
                binding.autoCbAcionado.setAdapter(adapterCbAcionado)
            }

            "6º SGBI Umuarama" -> {
                cbAcionado = resources.getStringArray(R.array.sgbiUmuarama)
                adapterCbAcionado =
                    ArrayAdapter(this, android.R.layout.simple_list_item_1, cbAcionado)
                binding.autoCbAcionado.setAdapter(adapterCbAcionado)
            }

            "7º SGBI Santo Antonio da Platina" -> {
                cbAcionado = resources.getStringArray(R.array.sgbiSap)
                adapterCbAcionado =
                    ArrayAdapter(this, android.R.layout.simple_list_item_1, cbAcionado)
                binding.autoCbAcionado.setAdapter(adapterCbAcionado)
            }

            "8º SGBI Cianorte" -> {
                cbAcionado = resources.getStringArray(R.array.sgbiCianorte)
                adapterCbAcionado =
                    ArrayAdapter(this, android.R.layout.simple_list_item_1, cbAcionado)
                binding.autoCbAcionado.setAdapter(adapterCbAcionado)
            }

            "9º SGBI Paranavaí" -> {
                cbAcionado = resources.getStringArray(R.array.sgbiParanavai)
                adapterCbAcionado =
                    ArrayAdapter(this, android.R.layout.simple_list_item_1, cbAcionado)
                binding.autoCbAcionado.setAdapter(adapterCbAcionado)
            }

            "10º SGBI Irati" -> {
                cbAcionado = resources.getStringArray(R.array.sgbiIrati)
                adapterCbAcionado =
                    ArrayAdapter(this, android.R.layout.simple_list_item_1, cbAcionado)
                binding.autoCbAcionado.setAdapter(adapterCbAcionado)
            }
        }

        txtEmpenhoVtr = binding.txtEmpenhoVtr

        adapter1 = AdapterViatura(lista, this, txtEmpenhoVtr)

        rv = binding.rvVtr
        rv.layoutManager = LinearLayoutManager(this)
        rv.adapter = adapter1

        Thread {
            val app = application as App
            val dao = app.db.daoViaturas()
            val resultado = dao.listarTodas()

            runOnUiThread() {
                lista.addAll(resultado)
                adapter1.notifyDataSetChanged()
            }
        }.start()

        val items = resources.getStringArray(R.array.tipologia)
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, items)
        binding.autoTipologia.setAdapter(adapter)

        binding.btnCadastrarVtr.setOnClickListener {
            val tipo = binding.autoTipologia.text.toString()
            val numeracao = binding.edtNumeracao.text.toString()
            val prefixo = tipo + numeracao

            when (tipo) {
                "ABTR:" -> {
                    cod = 1
                }

                "AA:" -> {
                    cod = 2
                }

                "ABS:" -> {
                    cod = 3
                }

                "ABT:" -> {
                    cod = 4
                }
            }

            Thread {
                val app = application as App
                val dao = app.db.daoViaturas()
                dao.inserirViatura(
                    Viaturas(
                        cod = cod,
                        prefixo = prefixo
                    )
                )
                runOnUiThread {
                    Toast.makeText(this, "Viatura cadastrada com sucesso!", Toast.LENGTH_SHORT)
                        .show()
                    lista.add(Viaturas(cod = cod, prefixo = prefixo))
                    adapter1.notifyItemInserted(lista.size)

                    val updatedItems = cbAcionado.toMutableList()
                    updatedItems.add(prefixo)
                    cbAcionado = updatedItems.toTypedArray()
                    adapterCbAcionado =
                        ArrayAdapter(this, android.R.layout.simple_list_item_1, cbAcionado)
                    binding.autoCbAcionado.setAdapter(adapterCbAcionado)
                }
            }.start()
        }

        binding.autoCbAcionado.addTextChangedListener(autoCompleteTextWatcher)
        binding.txtEmpenhoVtr.addTextChangedListener(autoCompleteTextWatcher)
        binding.autoTipologia.addTextChangedListener(autoCompleteTextWatcher)
        binding.edtEfetivo.addTextChangedListener(autoCompleteTextWatcher)
        binding.edtNumeracao.addTextChangedListener(autoCompleteTextWatcher)


        binding.radioGroup.setOnCheckedChangeListener { group, checkedId ->
            selectedOption = when (checkedId) {
                R.id.radioButtonSim -> "Sim"
                R.id.radioButtonNao -> "Não"
                else -> null
            }

            when (selectedOption) {
                "Sim" -> {
                    binding.btnAvancar.setOnClickListener {
                        val intent = Intent(this, Apoio::class.java)
                        val cbAcionado = binding.autoCbAcionado.text.toString()
                        val vtrEmpenhada = binding.txtEmpenhoVtr.text.toString()
                        val efetivo = binding.edtEfetivo.text.toString()

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

                        intent.putExtra("cbAcionado", cbAcionado)
                        intent.putExtra("vtrEmpenhada", vtrEmpenhada)
                        intent.putExtra("efetivo", efetivo)
                        startActivity(intent)
                    }
                }

                "Não" -> {
                    binding.btnAvancar.setOnClickListener {
                        val intent = Intent(this, Descritivo::class.java)
                        val cbAcionado = binding.autoCbAcionado.text.toString()
                        val vtrEmpenhada = binding.txtEmpenhoVtr.text.toString()
                        val efetivo = binding.edtEfetivo.text.toString()

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

                        intent.putExtra("cbAcionado", cbAcionado)
                        intent.putExtra("vtrEmpenhada", vtrEmpenhada)
                        intent.putExtra("efetivo", efetivo)
                        startActivity(intent)
                    }
                }
            }
        }
    }

    private val autoCompleteTextWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            val cbAcionado = binding.autoCbAcionado.text.toString()

            binding.btnAvancar.isEnabled =
                cbAcionado.isNotEmpty() && binding.txtEmpenhoVtr.text.isNotEmpty() && binding.edtEfetivo.text.isNotEmpty()
            binding.btnCadastrarVtr.isEnabled =
                binding.autoTipologia.text.isNotEmpty() && binding.edtNumeracao.text.isNotEmpty()

        }

        override fun afterTextChanged(s: Editable?) {
        }
    }

    override fun onClick(id: Int, type: String) {
    }

    override fun onLongClick(position: Int, viaturas: Viaturas) {
        AlertDialog.Builder(this)
            .setMessage(getString(R.string.msg_deletarViatura))
            .setNegativeButton(android.R.string.cancel) { dialog, which ->
            }
            .setPositiveButton(android.R.string.ok) { dialog, which ->
                Thread {
                    val app = application as App
                    val dao = app.db.daoViaturas()
                    val resultado = dao.deletar(viaturas)

                    if (resultado > 0) {
                        runOnUiThread() {
                            lista.removeAt(position)
                            adapter1.notifyItemRemoved(position)
                        }
                    }

                }.start()
            }
            .create()
            .show()
    }
}

//  private lateinit var fab: FloatingActionButton

// binding.edtEfetivo.requestFocus()
/*
fab = binding.btnEmpenhoVtr
fab.isEnabled = false
adicionar fab na frente do this
*/

/*
binding.btnEmpenhoVtr.setOnClickListener {
val selectedItems = adapter.getSelectedItems()
val selectedText = selectedItems.joinToString(", ") { it.prefixo }
binding.txtEmpenhoVtr.text = selectedText
}*/