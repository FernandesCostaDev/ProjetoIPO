package com.example.projetoipo.ui.home

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.fragment.app.Fragment
import com.example.projetoipo.InfoInicial
import com.example.projetoipo.MainActivity
import com.example.projetoipo.databinding.FragmentHomeBinding
import com.example.projetoipo.model.App

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var lista: MutableList<String>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lista = mutableListOf<String>()

        Thread {
            val app = requireActivity().application as App
            val dao = app.db.dao()
            val resultado = dao.buscarRegistro()
            val nomes = resultado.map {
                it.nome
            }
            requireActivity().runOnUiThread {
                lista.addAll(nomes)
                val adapter =
                    ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, lista)
                binding.autoNome.setAdapter(adapter)
            }
        }.start()

        binding.autoNome.addTextChangedListener(autoCompleteTextWatcher)

        binding.btnPreencher.setOnClickListener {

            val nomeID = binding.autoNome.text.toString()

            var receberNome = ""
            var receberGraduacao = ""
            var receberCrbm = ""
            var receberObm = ""

            Thread {
                val app = requireActivity().application as App
                val dao = app.db.dao()
                val resultado = dao.buscarRegistro2(nomeID)

                for (perfilUser in resultado) {
                    receberNome = perfilUser.nome.toString()
                    receberGraduacao = perfilUser.graduacao.toString()
                    receberCrbm = perfilUser.crbm.toString()
                    receberObm = perfilUser.obm.toString()
                }
                requireActivity().runOnUiThread {
                  val graduacaoNome = "$receberGraduacao $receberNome"

                    val intent = Intent(requireContext(),InfoInicial::class.java)
                    intent.putExtra("graduacaoNome", graduacaoNome)
                    intent.putExtra("crbm", receberCrbm)
                    intent.putExtra("obm", receberObm)
                    startActivity(intent)

                }
            }.start()
        }
    }

    private val autoCompleteTextWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            val nome = binding.autoNome.text
            binding.btnPreencher.isEnabled = nome.isNotEmpty()
        }

        override fun afterTextChanged(s: Editable?) {
        }
    }

}
