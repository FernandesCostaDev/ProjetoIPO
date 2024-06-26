package com.example.projetoipo.ui.cadUsuario

import android.content.Context
import android.content.Intent
import android.widget.ArrayAdapter
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import com.example.projetoipo.MainActivity
import com.example.projetoipo.R
import com.example.projetoipo.databinding.FragmentCadUsuarioBinding
import com.example.projetoipo.model.App
import com.example.projetoipo.model.Usuario

class CadUsuario : Fragment() {

    private lateinit var binding: FragmentCadUsuarioBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?): View {
       binding = FragmentCadUsuarioBinding.inflate(inflater,container,false)
       return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var nomeBd = ""
        var graduacaoBd = ""
        var crbmBd = ""
        var obmBd = ""
        var respcrbm = ""

        val itemsGraduacao = resources.getStringArray(R.array.graduacao)
        val adapterGraduacao =  ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, itemsGraduacao)
        binding.autoCompleteGraduacao.setAdapter(adapterGraduacao)

        val itemsCrbm = resources.getStringArray(R.array.crbm)
        val adapterCrbm =  ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, itemsCrbm)
        binding.autoCompleteCrbm.setAdapter(adapterCrbm)

        binding.autoCompleteCrbm.addTextChangedListener {
            respcrbm = binding.autoCompleteCrbm.text.toString()

            when (respcrbm) {
                "1º CRBM Curitiba" -> {
                    val itemsObm = resources.getStringArray(R.array.obm_1crbm)
                    val adapterObm =
                        ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, itemsObm)
                    binding.autoCompleteObm.setAdapter(adapterObm)
                }

                "2º CRBM Londrina" -> {
                    val itemsObm = resources.getStringArray(R.array.obm_2crbm)
                    val adapterObm =
                        ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, itemsObm)
                    binding.autoCompleteObm.setAdapter(adapterObm)
                }

                "3º CRBM Cascavel" -> {
                    val itemsObm = resources.getStringArray(R.array.obm_3crbm)
                    val adapterObm =
                        ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, itemsObm)
                    binding.autoCompleteObm.setAdapter(adapterObm)
                }
            }
        }

        binding.edtNome.addTextChangedListener(perfilTextWatcher)
        binding.autoCompleteGraduacao.addTextChangedListener(perfilTextWatcher)
        binding.autoCompleteCrbm.addTextChangedListener(perfilTextWatcher)
        binding.autoCompleteObm.addTextChangedListener(perfilTextWatcher)

        binding.txtNome.addTextChangedListener(perfilTextWatcher)
        binding.txtGraduacao.addTextChangedListener(perfilTextWatcher)
        binding.txtCrbm.addTextChangedListener(perfilTextWatcher)
        binding.txtObm.addTextChangedListener(perfilTextWatcher)

        binding.btnAdicionar.setOnClickListener {

            binding.txtNome.text = binding.edtNome.text
            binding.txtGraduacao.text = binding.autoCompleteGraduacao.text
            binding.txtCrbm.text = binding.autoCompleteCrbm.text
            binding.txtObm.text = binding.autoCompleteObm.text

            val service = requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            service.hideSoftInputFromWindow(requireActivity().currentFocus?.windowToken,0)
        }

        binding.btnSalvar.setOnClickListener {

            nomeBd = binding.edtNome.text.toString()
            graduacaoBd = binding.autoCompleteGraduacao.text.toString()
            crbmBd = binding.autoCompleteCrbm.text.toString()
            obmBd = binding.autoCompleteObm.text.toString()

            Thread {
                val app = requireActivity().application as App
                val dao = app.db.dao()
                dao.inserir(
                    Usuario(
                        nome = nomeBd,
                        graduacao = graduacaoBd,
                        crbm = crbmBd,
                        obm = obmBd
                    )
                )
                requireActivity().runOnUiThread {
                    Toast.makeText(requireContext(), "Bombeiro salvo com sucesso! \uD83E\uDDD1\u200D\uD83D\uDE92", Toast.LENGTH_SHORT).show()

                    val intent = Intent(requireContext(), MainActivity::class.java)
                    startActivity(intent)

                }
            }.start()
        }

    }
    private val perfilTextWatcher = object: TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            val nome = binding.edtNome.text
            val graduacao = binding.autoCompleteGraduacao.text
            val crbm = binding.autoCompleteCrbm.text
            val obm = binding.autoCompleteObm.text

            val txtNome = binding.txtNome
            val txtGraduacao = binding.txtGraduacao
            val txtCrbm = binding.txtCrbm
            val txtObm = binding.txtObm

            binding.btnAdicionar.isEnabled = nome.isNotEmpty() && graduacao.isNotEmpty() && crbm.isNotEmpty() && obm.isNotEmpty()
            binding.btnSalvar.isEnabled = txtNome.text.isNotEmpty() && txtGraduacao.text.isNotEmpty() && txtCrbm.text.isNotEmpty() && txtObm.text.isNotEmpty()

        }
        override fun afterTextChanged(s: Editable?) {
        }
    }
}

